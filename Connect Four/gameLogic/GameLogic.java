package gameLogic;
import java.util.*;

public class GameLogic {
	
	// handles all of the game logic 
	
	public static void main (String args[]){
		String input = null;
		boolean gameDone = false;
		PlayerOne p1 = new PlayerOne();
		PlayerTwo p2 = new PlayerTwo();
		Scanner sc = new Scanner(System.in);
		
		// grabs the state of the game board 
		GameBoard aBoard = new GameBoard();
		char[][] board = aBoard.getBoard();
		

		// game loop
		while (gameDone != true){
			
			// asks for input for player 1 row move 
			System.out.println("Player One Move {R}: ");
			int p1r = sc.nextInt();
			// asks for input for player 1 column move 
			System.out.println("Player One Move {C}: ");
			int p1c = sc.nextInt();
			// places move on the board 
			board[p1r][p1c] = p1.playerPiece;
			// checks win condition 
			printBoard(board);
			if (checkWin(board, p1.playerPiece) || checkDraw(board)){
				gameDone = true;
				break;
			}
			// asks for input for player 2 row move
			System.out.println("Player Two Move {R}: ");
			int p2r = sc.nextInt();
			// asks for input for player 2 column move
			System.out.println("Player Two Move {C}: ");
			int p2c = sc.nextInt();
			// places move on the board 
			board[p2r][p2c] = p2.playerPiece;
			printBoard(board);
			// checks win and draw condition 
			if(checkWin(board, p2.playerPiece) || checkDraw(board)){
				gameDone = true;
				break;
			}
			
		}

	}

	// Checks win condition
	public static boolean checkWin(char board[][], char token){
		if(checkHorizontalWin(board, token)  || checkVerticalWin(board, token) )
			return true;
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
        for (int i = 0; i < 6; i++){
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
					System.out.println("game over");
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
					System.out.println("game over");
					return true;
				}
			}
		}
		return false;
	}
	
	// check diagonal win
	// not yet implemented 
	public boolean checkDiagonalWin(){
		return true;
	}
}
