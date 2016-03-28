package gameLogic;

import java.util.*;

public class GameLogic {
	
	// handles all of the game logic 
	
	private static Scanner sc;
	private static PlayerOne p1;
	private static PlayerTwo p2;

	public static void main (String args[]){
		boolean gameDone = false;
		boolean validMoveP1 = false;
		boolean validMoveP2 = false;
		setP1(new PlayerOne());
		setP2(new PlayerTwo());
		sc = new Scanner(System.in);
		String move = null;
		// grabs the state of the game board 
		GameBoard aBoard = new GameBoard();
		char[][] board = aBoard.getBoard();
		

		// game loop
		while (gameDone != true){
			validMoveP1 = false;
			validMoveP2 = false;
			int p1c = -1;
			int p2c = -1;
			while(validMoveP1 != true){
				printBoard(board);
				System.out.println("Player One Select A Column: ");
				p1c = sc.nextInt();
				validMoveP1 = validMove(p1c, board, p1.playerPiece);
				System.out.println("-----------------------------");
				
			}

			if (checkWin(board, PlayerOne.playerPiece) || checkDraw(board)){
				move = p1c + "PlayerOneWin" + p1.playerName; // this is what will be sent to the server
				printBoard(board); // should be sent from the server 
				break;
			}
			move = p1c + "" + p1.playerName; // this is what will be sent to the server
			while(validMoveP2 != true){
				printBoard(board);
				System.out.println("Player Two Select A Column: ");
				p2c = sc.nextInt();
				validMoveP2 = validMove(p2c, board, p2.playerPiece);
				System.out.println("-----------------------------");
			}
			
			if (checkWin(board, PlayerTwo.playerPiece) || checkDraw(board)){
				move = p2c + "PlayerTwoWin" + p2.playerName; // this is what will be sent to the server
				printBoard(board); // should be sent from the server
				break;
			}
			move = p2c + "" + p2.playerName; // this is what will be sent to the server
		}
	}
	
	public static boolean validMove(int move, char board[][], char token){
		if(move > 6){
			return false;
		}
		else if(board[5][move] == '.'){
			board[5][move] = token;
			return true;
		}
		else if(board[4][move] == '.'){
			board[4][move] = token;
			return true;
		}
		else if(board[3][move] == '.'){
			board[3][move] = token;
			return true;
		}
		else if(board[2][move] == '.'){
			board[2][move] = token;
			return true;
		}
		else if(board[1][move] == '.'){
			board[1][move] = token;
			return true;
		}
		else if(board[0][move] == '.'){
			board[0][move] = token;
			return true;
		}
		return false;
	}

	// Checks win condition
	public static boolean checkWin(char board[][], char token){
		if(checkHorizontalWin(board, token)  || checkVerticalWin(board, token) || checkDiagonalDown(board, token) || checkDiagonalUp(board, token)){
			System.out.println("game over");
			if(token == 'B')
				System.out.println("Player one wins!!");
			else if(token == 'R')
				System.out.println("Player two wins!!");
			return true;
		}
			
		//checkDraw(board);
		return false;
	}
	
	// Checks draw
	public static boolean checkDraw(char board[][]){
		int counter = 0;
		for(int row = 0; row < 6; row++){
			for(int col = 0; col < 7; col++){
				if(board[row][col] == '.'){
					counter++;
				}
			}
		}
		if(counter == 0){
			System.out.println("DRAW");
			return true;
		}
		return false;
	}

	// will print the board 
    private static void printBoard(char [][] board){
    	System.out.println("  0 1 2 3 4 5 6");
        for (int i = 0; i < 6; i++){
        	System.out.print(i);
            for(int j = 0; j < 7; j++){
                System.out.print("|"+board[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }
    
    // check horizontal win 
	public static boolean checkHorizontalWin(char aBoard[][], char token){
		int counter = 0;
		for(int row = 0; row < 6; row++){
			for(int col = 0; col < 7; col++){
				if(aBoard[row][col] == token){
					counter++;
				}
				else
					counter = 0;
				if(counter == 4){
					return true;
				
				}
			}
		}
		return false;
	}
	
	// check vertical win
	public static boolean checkVerticalWin(char aBoard[][], char token){
		int counter = 0;
		for(int col = 0; col < 7; col++){
			for(int row = 0; row < 6; row++){
				if(aBoard[row][col] == token){
					counter++;
				}
				else
					counter = 0;
				if(counter == 4){
					return true;
				}
			}
		}
		return false;
	}



		//Check for 4 tokens in a row diagonally down to the right
	static boolean checkDiagonalDown(char board[][], char token) {
	     int row, col;		          //row,column variables
	     //Check diagonal "\"
	     for (row=0; row<=2; row++) {	//diagonal winner must include rows 0, 1, or 2
	       for (col=0; col<=3; col++) {//diagonal winner must include columns 0, 1, 2, or 3
		 if (board[row][col] == token &&    //this cell has token
		   board[row+1][col+1] == token &&    //cell one down, one right has token
		   board[row+2][col+2] == token &&    //cell two down, two right has token
		   board[row+3][col+3] == token) {   //cell three down, three right has token
		     return true;		      //we have a winner
		    }
		  }
	         }
	       return false;
	}

	//Check for 4 tokens in a row diagonally down to the left
	static boolean checkDiagonalUp(char board[][], char token) {
		int row, col;			//row,column variables
		//Check diagonal "/"
		for (row=0; row<=2; row++) {	  //4 diagonal must include rows 0, 1, or 2
		    for (col=3; col<=6; col++){ //4 diagonal must include columns 3, 4, 5, or 6
			if (board[row][col] == token &&      //this cell has token
			    board[row+1][col-1] == token &&  //one down, one left hast token
			    board[row+2][col-2] == token &&  //two down, two left has token
			    board[row+3][col-3] == token){ //three down, three left has token
				return true;	        //we have a winner
			 }
		      }
		  }
		      return false;
	}

	public static PlayerOne getP1() {
		return p1;
	}

	public static void setP1(PlayerOne p1) {
		GameLogic.p1 = p1;
	}

	public static PlayerTwo getP2() {
		return p2;
	}

	public static void setP2(PlayerTwo p2) {
		GameLogic.p2 = p2;
	}
}
