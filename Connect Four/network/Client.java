package network;

/* umm create a client object fill in the methods connect to the server with the same port. The server will
 * have like secondary ports for everyone thats connected so don't worry about it.
 * PEOPLE ARE PORTS UNTIL WE CAN LINK THEM WITH ACCOUNTS OR SOMETHING w.e this is a crappy version
 * 
 */


import java.io.*; 
import java.net.*; 
import java.util.stream.Stream;

public class Client{ 

	private Socket clientSocket;
	private DataOutputStream outBuffer;
	private DataInputStream inBuffer;
	
	public Client(String IP, int port) throws Exception
	{
		
        // Initialize a client socket connection to the server
        clientSocket = new Socket(IP, port);

        // Initialize input and an output stream for the connection(s)
        outBuffer = new DataOutputStream(clientSocket.getOutputStream()); 
        inBuffer = new DataInputStream(clientSocket.getInputStream()); 

    }
    // send terminate receive who knows you terminated loool
    public void terminate() throws IOException{
    	String line = "terminate";
    	// Send to the server
        outBuffer.writeBytes(line + '\n'); 
        
    	// Close the socket
        clientSocket.close();
    	
    }
    
    public void logout() throws IOException
    {
    	String line = "logout";
    	outBuffer.writeBytes(line + '\n');
    	//clientSocket.close();
    }
    
    public void login() throws IOException
    {
    	//needs to check the db and verify user creds
    	String line = "login";
    	outBuffer.writeBytes(line + '\n');
    }
    
    public void createGame(String playerName) throws IOException
    {
    	
    	String line = "create";
    	outBuffer.writeBytes(line + '\n');
    }
    
    public void refresh() throws IOException
    {
    	//get player list
    	String line = "refresh";
    	outBuffer.writeBytes(line + '\n');
    	
    	line = inBuffer.readLine();
    	System.out.println("Server: " +line);
    	//parseString(line);
    }
    
    private void parseString(String info)
    {
    	String playersList;
    	for()
    }
}

