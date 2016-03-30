package network;

import gameLogic.GameBoard;

/* just hold the room information
 * 
 */
public class Room {
	String player1 = "";
	String player2 = "";
	int turn = 1; //(can be 1 or 2)
	boolean joinable = true;
	String[] spectators;
	String[] roomMessages;
	GameBoard board;
	
	Room(String player){
		player1 = player;
	}
}
