package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnect {

	public Connection SQLConnection() {
		Connection con = null;
		try {
			String host = "jdbc:mysql://localhost:3306/connectfour";
			String username = "root";
			String password = "nic12345";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(host, username, password);
			System.out.println("Successfuly connected to: " + host);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch ( SQLException err ) {
			System.out.println( err.getMessage( ) );
		}
		return con;
	}
}

