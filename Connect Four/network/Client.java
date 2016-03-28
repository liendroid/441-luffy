package network;

/* umm create a client object fill in the methods connect to the server with the same port. The server will
 * have like secondary ports for everyone thats connected so don't worry about it.
 * PEOPLE ARE PORTS UNTIL WE CAN LINK THEM WITH ACCOUNTS OR SOMETHING w.e this is a crappy version
 * 
 */


import java.io.*; 
import java.net.*; 
import java.util.stream.Stream;

public class Client { 

    public void create(String IP, String port) throws Exception 
    { 
        // Initialize a client socket connection to the server
        Socket clientSocket = new Socket(IP, Integer.parseInt(port));

        // Initialize input and an output stream for the connection(s)
        DataOutputStream outBuffer = 
          new DataOutputStream(clientSocket.getOutputStream()); 
        DataInputStream inBuffer = 
          new DataInputStream(clientSocket.getInputStream()); 

        // Get user input and send to the server
 
        //when you create a client it should stay open until w.e, its a class so you can open it with you GUI or w.e
        while (//terminate not pressed or true) // change to while the terminate button or "x" or  w.e you use has not been pressed
        {
            // you can keep on doing w.e you want until terminate is true
        }

        // Close the socket
        clientSocket.close();           
    }
    // send terminate receive who knows you terminated loool
    public void terminate(DataOutputStream outBuffer, DataInputStream inBuffer, Socket clientSocket) throws IOException{
    	String line = "terminate";
    	// Send to the server
        outBuffer.writeBytes(line + '\n'); 
        
        //from the server
    	line = inBuffer.readLine();
    	System.out.println("Server: " +line);
    	// Close the socket
        clientSocket.close();
    	
    }
    // send login receive everyone else in the room in like a string list 45,34,23 ... only going to be in ports for now
    public String login(DataOutputStream outBuffer, DataInputStream inBuffer) throws IOException{
    	String line = "login";
    	// Send to the server
        outBuffer.writeBytes(line + '\n'); 
        
        //from the server
    	line = inBuffer.readLine();
    	System.out.println("Server: " +line);
    	
    	return line;
    }
} 

