package gameLogic;


public class GameBoard {

	//creates the board and handle the board properties 
	
	public char[][] board = new char[6][7];
	
	public GameBoard(){
		
		for (int row = 0; row < 6; row++)
			for(int col = 0; col < 7; col ++)
				board[row][col] = '.';
	}
	
	public char[][] getBoard(){
		return board;
	}
}
