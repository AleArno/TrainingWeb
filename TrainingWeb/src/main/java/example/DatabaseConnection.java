package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

	private Connection con;
	private static DatabaseConnection instance;

	private DatabaseConnection() {
	}

	public static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}

	public void start() {
//		try {
//			Class.forName("com.springsource.com.mysql.jdbc");
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
		con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/people", "root", "");
		} catch (SQLException e) {
			System.err.println("Connection Failed");
			e.printStackTrace();

		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}