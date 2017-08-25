package phone_adress_book;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class CreateDataBase {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		String query = "CREATE DATABASE phonebook";

		// first connection to create database
		Connection connection = ConnectMySQL.getConnection("jdbc:mysql://localhost/?useSSL=false");

		Statement stmt = connection.createStatement();

		stmt.executeUpdate(query);

		ConnectMySQL.closeConnection();

		// second connection with the database's name
		connection = ConnectMySQL.getConnection("jdbc:mysql://localhost/phonebook?useSSL=false");
		stmt = connection.createStatement();

		// query for table user
		query = " CREATE TABLE user (username VARCHAR (40) PRIMARY KEY NOT NULL,password INTEGER (4),email VARCHAR(40));";
		stmt.executeUpdate(query);

		query = " INSERT INTO user VALUES('jasmina1122',1122,'jasmina982@hotmail.com'),('dejan1111',1111,'dejan@gmail.com')";
		stmt.executeUpdate(query);

		// query for table contacts
		query = " CREATE TABLE contacts (numOfContact INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT,"
				+ "firstName VARCHAR(20),lastName VARCHAR (20),adress VARCHAR(70),email VARCHAR(40),phone VARCHAR(15),username VARCHAR(40),"
				+ "FOREIGN KEY(username) REFERENCES user(username));";

		stmt.executeUpdate(query);

		query = " INSERT INTO contacts (firstName,lastName,adress,email,phone,username) VALUES("
				+ "'Dino','Kurtovic','Miricina','nodispajidi@hotmail.com','//','jasmina1122'),("
				+ "'Jasmina','Kurtovic','Miricina','jasmina982@hotmail.com','010 222 333','jasmina1122'),('Mersed','Kurtovic','Miricina','mersed980@hotmail.com','//','jasmina1122'),"
				+ "('David','Musliu','Miricina','david@hotmail.com','061 112 131','jasmina1122'),"
				+ "('Tamara','Paocic','Tuzla','tamara.paocic@hotmail.com','060 775 316','dejan1111'),"
				+ "('Dzenan','Helic','Gracanica','dzenan_helic@hotmail.com','060 888 321','dejan1111'),"
				+ "('Jasmin','Mustafic','Klokotnica','mustaficjasmin7@gmail.com','060 668 316','dejan1111')";
		stmt.executeUpdate(query);
		System.out.println("Database phonebook created!");

		ConnectMySQL.closeConnection();
	}

}
