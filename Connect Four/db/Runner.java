package db;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;


public class Runner {
	
	public static void main(String[] args) {
		
		Scanner userInput = new Scanner(System.in);
		DBConnect connection = new DBConnect();
		SQLQueries queries = new SQLQueries();
		boolean loggedOut = false;
		
		while(!loggedOut) {
			
			System.out.println("What would you like to do?");
			System.out.println("1: for a list of all users.");
			System.out.println("2: for a users wins");
			System.out.println("3: for a users losses.");
			System.out.println("4: for adding a user");
			System.out.println("5: for deleting a user");
			System.out.println("Logout to stop the program.");
			
			String input = userInput.nextLine();
			if(input.equals("1")){
			
				try {
					queries.viewUsers(connection.SQLConnection(), "connectfour");
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else if (input.equals("2")) {
				
				try {
					System.out.println("Enter the user you wish to search");
					String myInput = userInput.nextLine();
					queries.viewWins(connection.SQLConnection(), "connectfour", myInput);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			} else if (input.equals("3")) {
				
				try {
					System.out.println("Enter the user you wish to search");
					String myInput = userInput.nextLine();
					queries.viewLosses(connection.SQLConnection(), "connectfour", myInput);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else if (input.equals("4")) {
			
				try {
					System.out.println("Enter your username");
					String newUsername = userInput.nextLine();
					System.out.println("Enter your password");
					String newPassword = userInput.nextLine();
					queries.addUser(connection.SQLConnection(), "connectfour", newUsername, newPassword);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else if (input.equals("5")) {
				
				try {
					System.out.println("Enter the username you wish to delete");
					String oldUsername = userInput.nextLine();
					System.out.println("Enter the password for the username you wish to delete");
					String oldPassword = userInput.nextLine();
					queries.removeUser(connection.SQLConnection(), "connectfour", oldUsername, oldPassword);
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			} else if (input.equals("Logout")) {
					
				loggedOut = true;
			}
		}
	}
}
