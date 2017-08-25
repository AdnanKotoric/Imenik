package phone_adress_book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataBaseHandling {
	// open connection
	static Connection connection = ConnectMySQL.getConnection("jdbc:mysql://localhost/phonebook?useSSL=false");

	static PreparedStatement stmt = null;
	static String query = "";
	static ResultSet rs = null;

	// method returns list which contains all users from table user
	public static Map<String, Integer> getUsers() {

		Map<String, Integer> userChek = new HashMap<>();
		try {
			query = "SELECT * FROM user ";
			stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CLOSE_CURSORS_AT_COMMIT);

			rs = stmt.executeQuery();

			while (rs.next()) {

				userChek.put(rs.getString("username"), rs.getInt("password"));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e);
		}

		return userChek;

	}

	public static String getUsersEmail(String username) {
		String email = "";
		try {
			query = " SELECT* FROM user WHERE username = '" + username + "'";

			stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();

			rs.next();
			email = rs.getString("email");

			rs.close();
		} catch (SQLException e) {
			System.err.println(e);
		}

		return email;
	}

	public static Map<Integer, String> getCorespondentPrimaryKey(String username) {

		Map<Integer, String> contactsNumber = new HashMap<>();
		try {
			query = " SELECT* FROM contacts WHERE contacts.username = '" + username + "'";

			stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();

			int i = 1;

			while (rs.next()) {

				contactsNumber.put(i, rs.getString("numOfContact"));
				i++;
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e);
		}

		return contactsNumber;
	}

	// add new user to the database
	public static void addNewUserToTheDataBase(UsersProfile newUser) throws SQLException {

		String name = newUser.getName();
		int password = newUser.getPassword();
		String email = newUser.getEmail();

		System.out.println(name + " " + password + " " + email);

		query = " INSERT INTO user VALUES('" + name + "'," + password + ",'" + email + "')";
		stmt = connection.prepareStatement(query);
		stmt.executeUpdate();
	}

	// add new contact to the database
	public static void addNewContactToTheDataBase(String newUser, ContactsProfile newContact) throws SQLException {
		String firstName = newContact.getFirstName();
		String lastName = newContact.getLastName();
		String address = newContact.getAddress();
		String email = newContact.getEmail();
		String phone = newContact.getPhoneNumber();

		query = " INSERT INTO contacts(firstName,lastName,adress,email,phone,username) VALUES('" + firstName + "','"
				+ lastName + "','" + address + "','" + email + "','" + phone + "','" + newUser + "')";
		stmt = connection.prepareStatement(query);
		stmt.executeUpdate();
	}

	// print all contact's info from this user
	public static void listOfAllContacts(String username) throws SQLException {

		query = " SELECT* FROM contacts  WHERE username = '" + username + "'";

		stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery();

		int i = 1;
		while (rs.next()) {

			System.out.println("Contact nb." + i + " - " + rs.getString("firstName") + " " + rs.getString("lastName")
					+ ", " + rs.getString("adress") + ", " + rs.getString("contacts.email") + ", "
					+ rs.getString("phone"));
			i++;
		}

		if (rs.getRow() == 0)
			System.out.println("\nNo contacts!");
		rs.close();

		System.out.println();
	}

	public static void editContact(String username, int contactNumber, ContactsProfile contact) throws SQLException {

		Map<Integer, String> contactsNumber = getCorespondentPrimaryKey(username);

		String primaryKey = contactsNumber.get(contactNumber);
		System.out.println(primaryKey);

		query = " UPDATE contacts SET contacts.firstName='" + contact.getFirstName() + "',contacts.lastName='"
				+ contact.getLastName() + "', contacts.adress='" + contact.getAddress() + "',contacts.email='"
				+ contact.getEmail() + "', contacts.phone='" + contact.getPhoneNumber()
				+ "' WHERE contacts.username = '" + username + "' AND contacts.numOfContact =  " + primaryKey;

		stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		stmt.executeUpdate();
		System.out.println("Contact edited! ");

	}

	// delete one contact based on user's choice
	public static void deleteContact(String username, int userOption) throws SQLException {

		// number of contacts before action
		int startRowNum = getNumberOfContacts(username);
		Map<Integer, String> contactsNumber = getCorespondentPrimaryKey(username);

		String primaryKey = contactsNumber.get(userOption);
		System.out.println(primaryKey);

		query = " DELETE phonebook.contacts FROM phonebook.user INNER JOIN phonebook.contacts ON user.username = contacts.username WHERE user.username = '"
				+ username + "' AND contacts.numOfContact =  " + primaryKey;

		stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		stmt.executeUpdate();
		// number of contacts after action
		int endRowNum = getNumberOfContacts(username);

		// if number of contact is changed,succes!
		if (startRowNum > endRowNum) {
			System.out.println("Contact deleted!");
		}
		// if number of contact is not changed,return to main menu
		else {
			System.out.println("You entered wrong number of contact!");
			MenuOptionsHandeling.printMenu(username);
		}
	}

	// method returns number of contacts,needed for checking in deleteContact()
	// method and limiting user's input
	public static int getNumberOfContacts(String username) throws SQLException {
		int numerOfContacts = 0;
		query = " SELECT* FROM contacts WHERE contacts.username = '" + username + "'";

		stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CLOSE_CURSORS_AT_COMMIT);
		rs = stmt.executeQuery();
		rs.last();
		numerOfContacts = rs.getRow();
		rs.close();
		return numerOfContacts;
	}

	// method returns contact's info based on primary key
	public static ContactsProfile contactInfo(String username, int contactNumber) throws SQLException {

		Map<Integer, String> contactsNumber = getCorespondentPrimaryKey(username);

		String primaryKey = contactsNumber.get(contactNumber);

		query = " SELECT* FROM contacts WHERE contacts.username = '" + username + "' AND contacts.numOfContact =  "
				+ primaryKey;

		stmt = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs = stmt.executeQuery();
		rs.next();

		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String address = rs.getString("adress");
		String email = rs.getString("email");
		String phone = rs.getString("phone");

		rs.close();
		ContactsProfile contact = new ContactsProfile(firstName, lastName, address, email, phone);
		return contact;

	}

}
