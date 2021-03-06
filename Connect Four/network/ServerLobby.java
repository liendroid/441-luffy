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

import gameLogic.GameLogic;

public class ServerLobby {

	public static void main(String[] args) throws IOException {
		// Initialize the selector and register the server socket
		Selector selector = Selector.open();
		// create a server channel and make it non-blocking
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.configureBlocking(false);
		InetSocketAddress isa = new InetSocketAddress(Integer.parseInt(args[0]));
		channel.socket().bind(isa);
		channel.register(selector, SelectionKey.OP_ACCEPT);
		ArrayList<String> players = new ArrayList<String>();
		ArrayList<Room> rooms = new ArrayList<Room>();
		ArrayList<String> serverMessages = new ArrayList<String>();
		UserDB db = new UserDB();

		try {
			while (true) {
				if (selector.select(500) < 0) // monitor registered sockets
					System.exit(1);
				// get set of ready sockets
				Set readyKeys = selector.selectedKeys();
				Iterator readyItor = readyKeys.iterator();

				// walk through the ready set
				while (readyItor.hasNext()) {
					SelectionKey key = (SelectionKey) readyItor.next();
					readyItor.remove();

					// Accept new connections, if any
					if (key.isAcceptable()) {
						SocketChannel cchannel = ((ServerSocketChannel) key.channel()).accept();
						cchannel.configureBlocking(false);

						// Register the new connection for read operation
						cchannel.register(selector, SelectionKey.OP_READ);
					} else {
						// ..receive and send
						SocketChannel cchannel = (SocketChannel) key.channel();
						if (key.isReadable()) {
							Socket socket = cchannel.socket();
							System.out.println("Accept connection from " + socket.toString());

							// OPen input and output streams
							ByteBuffer inBuffer = ByteBuffer.allocateDirect(20000);
							CharBuffer cBuffer = CharBuffer.allocate(20000);
							String line = "";

							// read from socket
							int bytesRecv = cchannel.read(inBuffer);
							if (bytesRecv <= 0) {
								System.out.println("Read() error, or the connection closed");
								key.cancel(); // deregister the socket
								continue;
							}

							// receive from client
							inBuffer.flip();
							cBuffer = Charset.forName("ISO-8859-1").decode(inBuffer);
							line = cBuffer.toString();
							System.out.println("Client: " + line + "\n");

							if (line.equals("logout\n")) {
								String message = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(message)) {
										players.remove(users[i].getUsername());
										key.cancel();
									}

								}
							}
							/*
							 * every player that connects to the server should
							 * send login so their info can be saved and the
							 * other players connected to the lobby can be sent,
							 * right now I only keep track of port numbers we
							 * can link that to different users later
							 */

							/*
							 * you should be sending login nonstop so you can
							 * get an updated list of whos in the room, you just
							 * get a bunch of ports ie 565, 454 ,334 all
							 * connected. You need to open like 4 different
							 * ecplise windows to test this sends back an empty
							 * string if there is no one else but you in the
							 * lobby
							 */
							else if (line.contains("login")) {
								String[] messageInfo = line.split(" ");
								String playerName = messageInfo[1].trim();
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getUsername().equals(playerName))
										users[i].setPortNumber(Integer.parseInt(cleanMessage));
								}

								// if the port is not itself send it back
								System.out.println("login accepted");
								players.add(playerName);
							}

							// refreshes everything
							else if (line.equals("refresh\n")) {
									
								String playerName = " ";
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(cleanMessage))
										playerName = users[i].getUsername();
								}
								// if the port is not itself send it back
								String list = "PLAYERS[";
								for (int i = 0; i < players.size(); i++) {
									if (!players.get(i).equals(playerName))
										list += players.get(i) + ",";
								}
								list = list + " [ROOMS[ ";
								for (int i = 0; i < rooms.size(); i++) {
									Room gameroom = rooms.get(i);

									list += " " + gameroom.player1 + " VS " + gameroom.player2 + " ";
									if (gameroom.joinable)
										list += "   Joinable";
									else
										list += "   Spectate";
									list = list + ",";

								}

								list = list + "[serverChatLog[ ";
								if (serverMessages.isEmpty())
									list = list + "[";
								for (int i = 0; i < serverMessages.size(); i++) {
									list += " " + serverMessages.get(i) + ",";
								}
								System.out.println(list);
								list = list + "\n";
								byte[] ba = list.getBytes("ISO-8859-1");
								ByteBuffer send = ByteBuffer.wrap(ba);
								cchannel.write(send);

							} else if (line.equals("create\n")) { // create a
																	// game room
								System.out.println("creating room\n");
								String port = getPort(cchannel);
								String playerName = " ";
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}
								Room game = new Room(playerName);
								rooms.add(game);

							} else if (line.equals("port\n")) {
								String port = getPort(cchannel);
								System.out.println(port);
								port = port + "\n";
								cchannel.write(ByteBuffer.wrap(port.getBytes()));

							} else if (line.contains("serverMessage[ ")) {
								String[] message = line.split("\\[");
								String[] message2 = message[1].split(":");
								String port = message2[0].trim();
								User[] users = db.getUserDB();
								String playerName = " ";
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}
								String convertedMessage = playerName + ": " + message2[1].trim();
								serverMessages.add(convertedMessage);
								System.out.println("adding message");
								System.out.println(serverMessages.size());

							} else if (line.contains("Join")) {
								String port = getPort(cchannel);
								User[] users = db.getUserDB();
								String playerName = " ";
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}

								String[] roomInfo = line.split(" ");
								String roomNumber = roomInfo[1].trim();
								System.out.println(roomNumber);
								int num = Integer.parseInt(roomNumber);
								Room game = rooms.get(num - 1);
								if (game.player1.equals(" "))
									game.player1 = playerName;
								else
									game.player2 = playerName;
								if (!game.player1.equals(" ") && !game.player2.equals(" "))
									game.joinable = false;

							} else if (line.contains("Leave")) {
								GameLogic logic = new GameLogic();
								String[] roomInfo = line.split(" ");
								String port = roomInfo[1].trim();
								User[] users = db.getUserDB();
								String playerName = " ";
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}
								System.out.println(playerName);
								// need to find the right room
								Boolean spectate = false;
								for (int i = 0; i < rooms.size(); i++) {
									Room game = rooms.get(i);
									if (game.player1.equals(playerName)) {
										game.player1 = " ";
										game.joinable = true;
									} else if (game.player2.equals(playerName)) {
										game.player2 = " ";
										game.joinable = true;
									}
									// remove them if they are a spectator
									for (int h = 0; h < game.spectators.size(); h++) {
										if (game.spectators.get(h).equals(playerName))
											spectate = true;
									}
									if(spectate)
										game.spectators.remove(playerName);
									//if the game is empty and there is a winner remove it we don't need it no more
									if(game.player1.equals(" ") && game.player2.equals(" ")){
										if(logic.checkWin(game.board.board, 'R'))
											rooms.remove(game);
										else if(logic.checkWin(game.board.board, 'B'))
											rooms.remove(game);
									}
								}
							}

							else if (line.contains("Spectate")) {
								String port = getPort(cchannel);
								User[] users = db.getUserDB();
								String playerName = " ";
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}

								String[] roomInfo = line.split(" ");
								String roomNumber = roomInfo[1].trim();
								System.out.println(roomNumber);
								int num = Integer.parseInt(roomNumber);
								Room game = rooms.get(num - 1);
								game.spectators.add(playerName);
							} else if (line.contains("gameMessage[ ")) {
								String[] message = line.split("\\[");
								String[] message2 = message[1].split(":");
								String port = message2[0].trim();
								User[] users = db.getUserDB();
								String playerName = " ";
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(port))
										playerName = users[i].getUsername();
								}
								String convertedMessage = playerName + ": " + message2[1].trim();
								// add it to the game lobby
								for (int i = 0; i < rooms.size(); i++) {
									if (rooms.get(i).player1.equals(playerName)
											|| rooms.get(i).player2.equals(playerName)) {
										rooms.get(i).roomMessages.add(convertedMessage);
										System.out.println("adding message");
										System.out.println(rooms.get(i).roomMessages.size());
									} else {
										for (int h = 0; h < rooms.get(i).spectators.size(); h++) {
											if (rooms.get(i).spectators.get(h).equals(playerName)) {
												rooms.get(i).roomMessages.add(convertedMessage);
												System.out.println("adding message");
												System.out.println(rooms.get(i).roomMessages.size());
											}
										}
									}
								}
							}

							// refreshes game
							else if (line.equals("refreshGame\n")) {
								String playerName = " ";
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(cleanMessage))
										playerName = users[i].getUsername();
								}
								// find the correct room to send data for
								int currentRoom = 0;
								for (int i = 0; i < rooms.size(); i++) {
									if (rooms.get(i).player1.equals(playerName)
											|| rooms.get(i).player2.equals(playerName))
										currentRoom = i;
									else {
										for (int h = 0; h < rooms.get(i).spectators.size(); h++) {
											if (rooms.get(i).spectators.get(h).equals(playerName))
												currentRoom = i;
										}
									}
								}
								// current room we are inside
								Room room = rooms.get(currentRoom);
								// if the person is a player print the opponent
								String list = "PLAYERS[ ";
								if (room.player1.equals(playerName) || room.player2.equals(playerName)) {
									if (!room.player1.equals(" ")) {
										if (!room.player1.equals(playerName))
											list = list + "OPPONENT: " + room.player1 + ",";
									}
									if (!room.player2.equals(" ")) {
										if (!room.player2.equals(playerName))
											list = list + "OPPONENT: " + room.player2 + ",";
									}
								}
								// person is a spectator list both players
								else {
									if (!room.player1.equals(" "))
										list = list + "Player 1: " + room.player1 + ",";
									if (!room.player2.equals(" "))
										list = list + "Player 2: " + room.player2 + ",";
								}

								list = list + " [SPECTATORS[ ";
								for (int i = 0; i < room.spectators.size(); i++) {
									list = list + "Spectator: " + room.spectators.get(i) + ",";
								}

								list = list + "[gameChatLog[ ";
								if (room.roomMessages.isEmpty())
									list = list + "[";
								for (int i = 0; i < room.roomMessages.size(); i++) {
									list += " " + room.roomMessages.get(i) + ",";
								}
								System.out.println(list);
								list = list + "\n";
								byte[] ba = list.getBytes("ISO-8859-1");
								ByteBuffer send = ByteBuffer.wrap(ba);
								cchannel.write(send);
							}
							
							else if(line.equals("PlayerName\n")){
								String playerName = " ";
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(cleanMessage)){
										playerName = users[i].getUsername();
									}
										
								}
								String role = " ";
								int turn = 1;
								for(int i = 0; i< rooms.size(); i++){
									if(rooms.get(i).player1.equals(playerName)){
										role = "player1";
										turn = rooms.get(i).turn;
									}
									else if(rooms.get(i).player2.equals(playerName)){
										role = "player2";
										turn = rooms.get(i).turn;
									}
									for(int h = 0; h< rooms.get(i).spectators.size(); h++){
										if(rooms.get(i).spectators.get(h).equals(playerName)){
											role = "spectator";
											turn = rooms.get(i).turn;
										}
									}
								}
								

								playerName = playerName + " " + role + " " + turn +  "\n";
								byte[] ba = playerName.getBytes("ISO-8859-1");
								ByteBuffer send = ByteBuffer.wrap(ba);
								cchannel.write(send);
							}
							
							else if(line.contains("Move")){
								String[] messageInfo = line.split(" ");
								char token = messageInfo[1].trim().charAt(0);
								String row = messageInfo[2].trim();
								String column = messageInfo[3].trim();
								
								// finding the playerName
								String playerName = " ";
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(cleanMessage))
										playerName = users[i].getUsername();
								}
								
								// find the correct room to send data for
								int currentRoom = 0;
								for (int i = 0; i < rooms.size(); i++) {
									if (rooms.get(i).player1.equals(playerName)
											|| rooms.get(i).player2.equals(playerName))
										currentRoom = i;
									else {
										for (int h = 0; h < rooms.get(i).spectators.size(); h++) {
											if (rooms.get(i).spectators.get(h).equals(playerName))
												currentRoom = i;
										}
									}
								}
								// current room we are inside
								Room room = rooms.get(currentRoom);
								room.board.board[Integer.parseInt(row)][Integer.parseInt(column)] = token;
								System.out.println(row + " " + column);
								room.turn = 1-room.turn;
								System.out.println(room.turn);
							}
							
							else if(line.equals("getBoard\n")){
								// finding the playerName
								String playerName = " ";
								String cleanMessage = getPort(cchannel);
								User[] users = db.getUserDB();
								for (int i = 0; i < users.length; i++) {
									if (users[i].getPortNumber() == Integer.parseInt(cleanMessage))
										playerName = users[i].getUsername();
								}
								
								// find the correct room to send data for
								int currentRoom = 0;
								for (int i = 0; i < rooms.size(); i++) {
									if (rooms.get(i).player1.equals(playerName)
											|| rooms.get(i).player2.equals(playerName))
										currentRoom = i;
									else {
										for (int h = 0; h < rooms.get(i).spectators.size(); h++) {
											if (rooms.get(i).spectators.get(h).equals(playerName))
												currentRoom = i;
										}
									}
								}
								// current room we are inside
								Room room = rooms.get(currentRoom);
								char[][] board = room.board.board;
								//change the 2D characters array into a string
								String parse = " ";
								for(int i = 0; i < board.length; i++){
									for(int l = 0; l <board[i].length; l++){
										parse += board[i][l] + ",";
									}
								}
								parse = parse.trim() + "\n";
								byte[] ba = parse.getBytes("ISO-8859-1");
								ByteBuffer send = ByteBuffer.wrap(ba);
								cchannel.write(send);
								System.out.println(parse);
							}

							// this was just for testing shit you should never
							// send the wrong string "DONT FORGET NEW LINES "/n"
							else {
								System.out.println("command does not exist");
								/*
								 * inBuffer.flip(); int bytesSent =
								 * cchannel.write(inBuffer); if(bytesSent !=
								 * bytesRecv ){ System.out.println(
								 * "Write() error, or the connection closed");
								 * key.cancel(); //deregister the socket }
								 */
							}
						}

					}
				}

			}
		} catch (IOException e) {
			System.out.println(e);
		}
		// close all connections
		Iterator itr = selector.keys().iterator();
		while (itr.hasNext()) {
			SelectionKey key = (SelectionKey) itr.next();
			if (key.isAcceptable())
				((ServerSocketChannel) key.channel()).socket().close();
			else if (key.isValid())
				((SocketChannel) key.channel()).socket().close();
		}
	}

	public static String getPort(SocketChannel cchannel) throws IOException {
		String port = cchannel.getRemoteAddress().toString();
		String[] message = port.split(",");
		message = message[0].split(":");
		String cleanMessage = message[1];
		return cleanMessage;
	}

}