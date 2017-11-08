package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

	private static Connection myConnection;

	public static Connection getConnecttion() {

		myConnection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			myConnection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/store?autoReconnect=true&useSSL=false", "root", "root");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return myConnection;
	}

}
