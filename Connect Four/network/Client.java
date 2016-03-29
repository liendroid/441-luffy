package network;

/* umm create a client object fill in the methods connect to the server with the same port. The server will
 * have like secondary ports for everyone thats connected so don't worry about it.
 * PEOPLE ARE PORTS UNTIL WE CAN LINK THEM WITH ACCOUNTS OR SOMETHING w.e this is a crappy version
 * 
 */


import java.io.*; 
import java.net.*;
import java.nio.ByteBuffer;
import java.util.stream.Stream;

public class Client{ 

	private Socket clientSocket;
	private DataOutputStream outBuffer;
	private DataInputStream inBuffer;
	private String[] players;
	private String[] rooms;
	
	public Client(String IP, int port) throws Exception
	{
		
        // Initialize a client socket connection to the server
        clientSocket = new Socket(IP, port);

        // Initialize input and an output stream for the connection(s)
        outBuffer = new DataOutputStream(clientSocket.getOutputStream()); 
        inBuffer = new DataInputStream(clientSocket.getInputStream()); 

    }
    public void logout() throws IOException
    {
    	String line = "logout\n";
    	outBuffer.write(line.getBytes("ISO-8859-1"));
    	clientSocket.close();
    }
    
    public void login() throws IOException
    {
    	//needs to check the db and verify user creds
    	String line = "login\n";
    	outBuffer.write(line.getBytes("ISO-8859-1"));
    }
    
    public void createGame(String playerName) throws IOException
    {
    	
    	String line = "create\n";
    	outBuffer.write(line.getBytes("ISO-8859-1"));
    }
    
    public void refresh() throws IOException
    {
    	//get player list
    	String line = "refresh\n";
    	outBuffer.write(line.getBytes("ISO-8859-1"));
    	
    	line = inBuffer.readLine();
    	System.out.println("Server: " +line);
    	String[] message = line.split(":");
    	String players = message[1];
    	String rooms = message[3];
    	
    	this.players = players.split(",");
    	this.rooms = rooms.split(",");
    }
	public String[] getPlayers() {
		return this.players;
	}
	public String[] getRooms() {
		// TODO Auto-generated method stub
		return this.rooms;
	}
	

}

