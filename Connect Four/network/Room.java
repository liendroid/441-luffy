package network;

import java.util.ArrayList;

import gameLogic.GameBoard;

/* just hold the room information
 * 
 */
public class Room {
	String player1 = " ";
	String player2 = " ";
	int turn = 1; // (can be 1 or 2)
	boolean joinable = true;
	ArrayList<String> spectators = new ArrayList<String>();
	ArrayList<String> roomMessages = new ArrayList<String>();
	GameBoard board;

	Room(String player) {
		player1 = player;
	}

	public boolean isEmpty() {
		if (this.player1.equals(" ") && this.player2.equals(" "))
			return true;
		return false;
	}
}
