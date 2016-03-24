package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLQueries {

	
	public void viewUsers(Connection myConnection, String myDataBase) throws SQLException {

		    Statement statement = null;
		    String query = "select username, password, wins, losses from " + myDataBase + ".users";
		   
		    try {
		        statement = myConnection.createStatement();
		        ResultSet results = statement.executeQuery(query);
		        while(results.next()) {
		        	String userName = results.getString("username");
		        	String passWord = results.getString("password");
		            int wins = results.getInt("wins");
		            int losses = results.getInt("losses");
		            System.out.println(userName + "\t" + passWord + "\t" + wins + "\t" + losses);
		        }
		    } catch (SQLException e) {
		        System.out.println(e);
		    } finally {
		        if (statement != null) { 
		        	statement.close(); 
		        }
		    }
		}
	
	public void viewWins(Connection myConnection, String myDataBase, String input) throws SQLException {
		
		Statement statement = null;
		String query = "select wins from " + myDataBase + ".users where username = '" + input + "'";
		
		try {
			statement = myConnection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int wins = results.getInt("wins");
				System.out.println(input + " has " + wins + " wins.");
			}
			
		} catch (SQLException e) {
	        System.out.println(e);
	    } finally {
	        if (statement != null) { 
	        	statement.close(); 
	        }
	    }
	}
	
	public void addUser(Connection myConnection, String myDataBase, String newUsername, String newPassword) throws SQLException {
		
		Statement statement = null;
		
		try {
			
			String query = "insert into " + myDataBase + ".users (username, password, wins, losses) " + 
					"values ('" + newUsername + "', '" + newPassword + "',0, 0)";
			statement = myConnection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
	        System.out.println(e);
	    } finally {
	        if (statement != null) { 
	        	statement.close(); 
	        }
	    }
	}
	
	public void removeUser(Connection myConnection, String myDataBase, String oldUsername, String oldPassword) throws SQLException {
		
		Statement statement = null;
		
		try {
			
			String query = "delete from " + myDataBase + ".users where username = '" 
			+ oldUsername + "' and password = '" + oldPassword + "'"; 
			statement = myConnection.createStatement();
			statement.executeUpdate(query);
			
		} catch (SQLException e) {
	        System.out.println(e);
	    } finally {
	        if (statement != null) { 
	        	statement.close(); 
	        }
	    }
	}
	
	public void viewLosses(Connection myConnection, String myDataBase, String input) throws SQLException {
		
		Statement statement = null;
		String query = "select losses from " + myDataBase + ".users where username = '" + input + "'";
		
		try {
			statement = myConnection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while(results.next()) {
				int losses = results.getInt("losses");
				System.out.println(input + " has " + losses + " losses.");
			}
			
		} catch (SQLException e) {
	        System.out.println(e);
	    } finally {
	        if (statement != null) { 
	        	statement.close(); 
	        }
	    }
	}
}
