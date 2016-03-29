package network;

/* server does not need a GUI you can just run it first and leave it going in the background, needs a port from the
 * command line or w.e, all clients should connect to this port
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class ServerLobby{
	
	public static void main(String[] args) throws IOException{
		// Initialize the selector and register the server socket
		Selector selector = Selector.open();
		//create a server channel and make it non-blocking
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		InetSocketAddress isa = new InetSocketAddress(Integer.parseInt(args[0]));
		channel.socket().bind(isa);
		channel.register(selector, SelectionKey.OP_ACCEPT);
		ArrayList<String> ports = new ArrayList<String>();
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		
		try {
			while(true){
				if (selector.select(500) < 0) // moniter registered sockets
					System.exit(1);
				// get set of ready sockets
				Set readyKeys = selector.selectedKeys();
				Iterator readyItor = readyKeys.iterator();
			
				//walk through the ready set
				while(readyItor.hasNext()){
					SelectionKey key = (SelectionKey)readyItor.next();
					readyItor.remove();
					
					//Accept new connections, if any
					if(key.isAcceptable()){
						SocketChannel cchannel = ((ServerSocketChannel)key.channel()).accept();
						cchannel.configureBlocking(false);
						
						//Register the new connection for read operation
						cchannel.register(selector, SelectionKey.OP_READ);
					}
					else {
					// ..receive and send
						SocketChannel cchannel = (SocketChannel)key.channel();
						if(key.isReadable()){
							Socket socket = cchannel.socket();
							System.out.println("Accept connection from " + socket.toString());
							
							//OPen input and output streams
							ByteBuffer inBuffer = ByteBuffer.allocateDirect(32);
							CharBuffer cBuffer = CharBuffer.allocate(32);
							String line = "";
							
							//read from socket
							int bytesRecv = cchannel.read(inBuffer);
							if(bytesRecv <= 0){
								System.out.println("Read() error, or the connection closed");
								key.cancel(); // deregister the socket
								continue;
							}
							
							// receive from client
			            	inBuffer.flip();
				            cBuffer = Charset.forName("ISO-8859-1").decode(inBuffer);
				            line = cBuffer.toString();
				            System.out.println("Client: " + line + "\n");
				            
				            if(line.equals("logout\n")) // if the player sends terminate close the socket
				            {
				            	String message = getPort(cchannel);
				            	ports.remove(message);
				            	key.cancel();
				            }
				            /* every player that connects to the server should send login so their info can be saved
				             	and the other players connected to the lobby can be sent, right now I only keep track of port
				             	numbers we can link that to different users later*/
				            
				            else if(line.equals("login\n")){ // you should be sending login nonstop so you can get an updated list of whos in the room, you just get a 
				            	//bunch of ports ie 565, 454 ,334 all connected. You need to open like 4 different ecplise windows to test this
				            	//sends back an empty string if there is no one else but you in the lobby
				            	String cleanMessage = getPort(cchannel);
				            	
				            	//printing the list
				            	String list ="PLAYERS:";
				            	for(int i =0; i< ports.size(); i++){
				            		list += ports.get(i)+",";
				            	}
				            	list = list + " :ROOMS: ";
				            	for(int i = 0; i<rooms.size();i++){
				            		Room gameroom = rooms.get(i);
				            		list += " " + gameroom.player1 + " VS " + gameroom.player2 +" "+ gameroom.joinable + ",";
				            	}
				            	System.out.println(list);
				            	list = list + "\n";
				            	byte[] ba = list.getBytes("ISO-8859-1");
				            	ByteBuffer send = ByteBuffer.wrap(ba);
				            	int bytesSent = cchannel.write(send);
				            	ports.add(cleanMessage);
				            }
				            else if(line.equals("refresh\n")){ //refreshes to see if anyone new has connected *also show active games, not complete yet*
				            	String cleanMessage = getPort(cchannel);
				            	//if the port is not itself send it back
				            	String list ="PLAYERS:";
				            	for(int i =0; i< ports.size(); i++){
				            		if(!ports.get(i).equals(cleanMessage))
				            			list += ports.get(i)+",";
				            	}
				            	list = list + " :ROOMS: ";
				            	for(int i = 0; i<rooms.size();i++){
				            		Room gameroom = rooms.get(i);
				            		list += " " + gameroom.player1 + " VS " + gameroom.player2 +" "+ gameroom.joinable + ",";
				            	}
				            	System.out.println(list);
				            	list = list + "\n";
				            	byte[] ba = list.getBytes("ISO-8859-1");
				            	ByteBuffer send = ByteBuffer.wrap(ba);
				            	int bytesSent = cchannel.write(send);
				            }
				            else if(line.equals("create\n")){ // create a game room 
				            	System.out.println("creating room\n");
				            	String port = getPort(cchannel);
				            	Room game = new Room(port);
				            	rooms.add(game);
				            	String message = "Room created\n";
				            	cchannel.write(ByteBuffer.wrap(message.getBytes("ISO-8859-1")));
				            	
				            }
				            // this was just for testing shit you should never send the wrong string "DONT FORGET NEW LINES "/n"
				            else{ 
				            	inBuffer.flip();
					            int bytesSent = cchannel.write(inBuffer);
					            if(bytesSent != bytesRecv ){
					            	System.out.println("Write() error, or the connection closed");
					            	key.cancel(); //deregister the socket
					            }
				            }
						}
						
						
					}
				}
			
			}
		}
		catch(IOException e) {System.out.println(e); }
		//close all connections
		Iterator itr = selector.keys().iterator();
		while(itr.hasNext()){
			SelectionKey key = (SelectionKey)itr.next();
			if(key.isAcceptable())
				((ServerSocketChannel)key.channel()).socket().close();
			else if(key.isValid())
				((SocketChannel)key.channel()).socket().close();
		}
	}
	public static String getPort(SocketChannel cchannel) throws IOException{
		String port = cchannel.getRemoteAddress().toString();
    	String[] message = port.split(",");
    	message = message[0].split(":");
    	String cleanMessage = message[1];
    	return cleanMessage;
	}

	
}