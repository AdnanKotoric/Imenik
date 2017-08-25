package phone_adress_book;

import java.sql.SQLException;

public class App {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		startApp();

	}

	public static void startApp() throws SQLException {

		System.out.println("----------------------------");
		System.out.println("-  A D D R E S S  B O O K  -");
		System.out.println("----------------------------");
		System.out.println("  Enter option:\n  1.Sign in\n  2.Sign up");

		int userOption = Inputs.userOption(2);

		if (userOption == 1)
			ManageUserRequest.singInOption();

		if (userOption == 2)
			ManageUserRequest.singUpOption();
	}

}
