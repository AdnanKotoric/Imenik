package phone_adress_book;

import java.util.Map;
import java.sql.SQLException;

public class ManageUserRequest {

	// getting all usernames and passwords from database
	static Map<String, Integer> users = DataBaseHandling.getUsers();

	// method that adds a new user to database
	public static void singUpOption() throws SQLException {

		System.out.println("Enter your name: ");
		String userName = Inputs.userStringInput();
		int num = 1000 + (int) (Math.random() * 9000);

		System.out.println("Enter your email:");
		String userEmail = Inputs.emailInput();

		while (userEmail.equals("//")) {
			System.out.println("Email is requaired!Enter email:");
			userEmail = Inputs.emailInput();
		}

		// create unique username
		while (users.containsKey(userName + num)) {
			num = 1000 + (int) (Math.random() * 9000);
		}

		int password = 1000 + (int) (Math.random() * 9000);
		users.put(userName + num, password);

		// create instance of class UsersProfile
		UsersProfile newUser = new UsersProfile(userName + num, password, userEmail);

		DataBaseHandling.addNewUserToTheDataBase(newUser);

		System.out.println("Your username: " + userName + num);
		System.out.println("Your password: " + password + "\n");

		MenuOptionsHandeling.printMenu(userName + num);

	}

	// method that validates users input(username and password)
	public static void singInOption() throws SQLException {

		System.out.println("Enter username: ");
		String userName = Inputs.userStringInput();
		int numOfTry = 1;

		// user can try 2 times to enter correct username
		while (!users.containsKey(userName) && numOfTry < 2) {
			System.out.println("There is no user " + userName + "! Check spelling! Try one more time: ");
			userName = Inputs.userStringInput();
			numOfTry++;
		}

		// if user enters wrong username 2 times
		if (numOfTry == 2 && !users.containsKey(userName)) {
			System.out.println("It seems you do not have access! We suggest you....singUp option");
			App.startApp();

		} else {

			int passwordFromDB = users.get(userName);
			System.out.println("Enter password:");

			int password = Inputs.user(passwordFromDB);
			numOfTry = 1;

			// if password isn't correct ask for email
			if (password == -1) {
				System.out.println("Can't remmember your password? Enter your email: ");
				String email = Inputs.emailInput();

				while (email.equals("//")) {
					System.out.println("Email is requaired for recovering password!Enter email:");
					email = Inputs.emailInput();
				}
				// if user enters correct email,print his password and main menu
				if (email.equals(DataBaseHandling.getUsersEmail(userName))) {
					System.out.println("Your password is : " + users.get(userName) + "\n");
					MenuOptionsHandeling.printMenu(userName);
				}
				// if user enters wrong password and email
				else {
					System.out.println(
							"Wrong email! If you can't remmember your password and email,you need to register again.");
					App.startApp();
				}
			} else {
				MenuOptionsHandeling.printMenu(userName);
			}

		}
	}

}