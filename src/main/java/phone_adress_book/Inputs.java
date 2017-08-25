package phone_adress_book;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Inputs {
	public static Scanner input = new Scanner(System.in);

	public static String userStringInput() {

		return input.nextLine();
	}

	// user's input for menu options
	public static int userOption(int limit) {

		boolean wrong = true;
		int userInput = 0;

		do {
			try {
				userInput = input.nextInt();
				input.nextLine();
				wrong = false;
				if (userInput < 1 || userInput > limit) {
					System.out.println("Wrong option!TryAgain!");
					wrong = true;

				}
			} catch (InputMismatchException ex) {
				System.out.println("Wrong input!Try again!");
				input.nextLine();
			}
		} while (wrong);
		return userInput;

	}

	// input of a contatc for deleting
	public static int userOptionForDelete(int limit) {

		boolean wrong = true;
		int userInput = 0;

		do {
			try {
				userInput = input.nextInt();
				input.nextLine();
				wrong = false;
				if (userInput < 0 || userInput > limit) {
					System.out.println("Wrong option!TryAgain!");
					wrong = true;

				}
			} catch (InputMismatchException ex) {
				System.out.println("Wrong input!Try again!");
				input.nextLine();
			}
		} while (wrong);
		return userInput;

	}

	// password input
	public static int user(int password) {

		boolean wrong = true;
		int userInput = 0;
		int numOfTry = 0;
		do {
			try {
				userInput = input.nextInt();
				input.nextLine();
				wrong = false;

				// user can try to enter a correct password 3 times
				if (password != userInput && numOfTry != 3) {
					System.out.println("Wrong password! Try again: ");

					numOfTry++;
					wrong = true;
				}

			} catch (InputMismatchException ex) {
				System.out.println("Wrong input!Try again!");
				numOfTry++;
				input.nextLine();
			}
		} while (wrong && numOfTry != 3);

		// if password isn't correct ask for email
		if (numOfTry == 3 && password != userInput) {
			userInput = -1;
		}
		return userInput;

	}

	// email input
	public static String emailInput() {

		String email = "";
		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		while (!email.matches(pattern) && !email.matches("//")) {
			email = input.nextLine();

			if (!email.matches(pattern) && !email.matches("//"))
				System.out.println("Email not valid!Try again!");
		}

		return email;
	}

	// email input for editing contact
	public static String emailInputForEdit() {

		// I don't expect anyone can crack this,starting value for email
		String email = "2w5R4TX123JK111?=/%#!:_;LHGD";

		String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		// old contact's email remains only if email is empty
		while (!email.matches(pattern) && !email.isEmpty()) {
			email = input.nextLine();

			if (!email.matches(pattern) && !email.isEmpty())
				System.out.println(
						"If you dont want to enter email,just skip by pressing ENTER or try again with valid email!");
		}

		return email;
	}

	public static String phone() {

		String phone = "";
		// 00 38762 123 123; +38735 717 171;
		String pattern1 = "0?0?[\\s-]?\\(?(\\d{5})?\\)?[\\s-]?\\d{3}[\\s-]?\\d{3}";
		// 061 111 111; 717 717
		String pattern2 = "(\\d{3})?[\\s-]?\\d{3}[\\s-]?\\d{3}";
		while (!phone.matches(pattern1) && !phone.matches(pattern2) && !phone.matches("//")) {
			phone = input.nextLine();

			if (!phone.matches(pattern1) && !phone.matches(pattern2) && !phone.matches("//"))
				System.out.println("Phone nuber not valid!Try again!");
		}

		return phone;
	}

	public static String phoneInputForEdit() {

		String phone = "2w5R4TX123JK111?=/%#!:_;LHGD";
		// 00 38762 123 123; +38735 717 171;
		String pattern1 = "0?0?[\\s-]?\\(?(\\d{5})?\\)?[\\s-]?\\d{3}[\\s-]?\\d{3}";
		// 061 111 111; 717 717
		String pattern2 = "(\\d{3})?[\\s-]?\\d{3}[\\s-]?\\d{3}";
		while (!phone.matches(pattern1) && !phone.matches(pattern2) && !phone.isEmpty()) {
			phone = input.nextLine();

			if (!phone.matches(pattern1) && !phone.matches(pattern2) && !phone.isEmpty())
				System.out.println(
						"If you dont want to enter phone number,just skip by pressing ENTER or try again with valid phone number!");
		}

		return phone;
	}
}
