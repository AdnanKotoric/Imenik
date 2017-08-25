package phone_adress_book;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectMySQL {

	static Connection connection = null;

	// open connection
	static public Connection getConnection(String CONN_STRING) {
		// values for connecting
		String USERNAME = "root";
		String PASSWORD = "toor";

		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println(e);
		}
		return connection;
	}

	// close connection
	static public void closeConnection() {

		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.out.println();
		}

	}

}
