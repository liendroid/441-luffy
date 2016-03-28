package network;
/* just hold the room information
 * 
 */
public class Room {
	String player1 = "";
	String player2 = "";
	int turn = 1; //(can be 1 or 2)
	boolean joinable = true;
	
	Room(String player){
		player1 = player;
	}
}
