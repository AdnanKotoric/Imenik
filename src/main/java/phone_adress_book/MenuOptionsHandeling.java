package phone_adress_book;

import java.sql.SQLException;

public class MenuOptionsHandeling {

	public static void printMenu(String userName) throws SQLException {

		System.out.println(
				"Enter the number for the option:\n1.Add new contact\n2.Edit contact\n3.Delete contact\n4.List of all contacts\n5.Log out");

		int userOption = Inputs.userOption(5);

		switch (userOption) {

		case 1:

			addNewContact(userName);
			break;
		case 2:
			editContact(userName);
			;
			break;
		case 3:
			deleteContact(userName);
			;
			break;
		case 4:
			System.out.println("User: " + userName + " -  List of all contacts : ");
			DataBaseHandling.listOfAllContacts(userName);
			;
			break;
		case 5:
			System.out.println("You're logging out!");
			App.startApp();
			;
			break;
		}
		printMenu(userName);
	}

	public static void addNewContact(String username) {

		System.out.println("Enter contact's first name, if there is no first name enter // : ");
		String firstName = Inputs.userStringInput();
		System.out.println("Enter last name, if there is no last name enter // : ");
		String lastName = Inputs.userStringInput();
		System.out.println("Enter address, if there is no address enter //: ");
		String address = Inputs.userStringInput();
		System.out.println("Enter email , if there is no email enter  // : ");
		String email = Inputs.emailInput();
		System.out.println("Enter phone number, if there is no phone number enter // : ");
		String phoneNumber = Inputs.phone();

		ContactsProfile newContact = new ContactsProfile(firstName, lastName, address, email, phoneNumber);
		try {
			DataBaseHandling.addNewContactToTheDataBase(username, newContact);
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public static void editContact(String username) throws SQLException {
		String message = "From the list above enter the nuber of the contact you want to edit.";
		int userOption = getUsersOption(username, message);

		if (userOption == 0)
			printMenu(username);

		else
			ManageContactEditing(username, userOption);

	}

	public static void ManageContactEditing(String username, int contactForEdit) throws SQLException {

		ContactsProfile contact = DataBaseHandling.contactInfo(username, contactForEdit);

		System.out.println("For edit :\nContact nb." + contactForEdit + ": " + contact.toString());

		System.out.println("Enter the first name,if you don't want to edit first name skip this by pressing ENTER: ");
		String first_name = Inputs.userStringInput();

		if (!first_name.isEmpty()) {
			contact.setFirstName(first_name);
		}

		System.out.println("Enter the last name,if you don't want to edit last name skip this by pressing ENTER: ");
		String last_name = Inputs.userStringInput();

		if (!last_name.isEmpty()) {
			contact.setLastName(last_name);
		}

		System.out.println("Enter the addres,if you don't want to edit address name skip this by pressing ENTER: ");
		String address = Inputs.userStringInput();

		if (!address.isEmpty()) {
			contact.setAddress(address);
		}
		System.out.println("Enter email,if you don't want to edit email skip this by pressing ENTER: ");
		String email = Inputs.emailInputForEdit();

		if (!email.equals(" ")) {
			contact.setEmail(email);
		}

		System.out.println("Enter phone numebr,if you don't want to edit phone number skip this by pressing ENTER: ");
		String phone = Inputs.phoneInputForEdit();

		if (!phone.isEmpty()) {
			contact.setPhoneNumber(phone);
		}

		DataBaseHandling.editContact(username, contactForEdit, contact);

		printMenu(username);

	}

	public static void deleteContact(String username) throws SQLException {

		String message = "From the list above enter the nuber of the contact you want to delete.";
		int userOption = getUsersOption(username, message);

		if (userOption == 0)
			printMenu(username);

		else
			DataBaseHandling.deleteContact(username, userOption);

	}

	// list contacts to user and returns user's input
	public static int getUsersOption(String username, String message) {

		int userOption = -1;
		try {

			// list all contacts first with order number
			DataBaseHandling.listOfAllContacts(username);

			System.out.println(message);
			System.out.println("For exit to main menu enter 0!");

			userOption = getUsersOption(username);

		} catch (SQLException e) {
			System.err.println(e);
		}
		return userOption;
	}

	// returns valid input from user
	public static int getUsersOption(String username) {
		// get last number of contact for input limit
		int numberOfContacts = 0;
		try {
			numberOfContacts = DataBaseHandling.getNumberOfContacts(username);
		} catch (SQLException e) {
			System.err.println(e);
		}
		int userOption = Inputs.userOptionForDelete(numberOfContacts);

		return userOption;
	}
}
