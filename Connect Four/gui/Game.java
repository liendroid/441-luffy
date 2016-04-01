package gui;

import gameLogic.GameBoard;
import gameLogic.GameLogic;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import network.Client;

import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.lang.reflect.Array;

import javax.swing.JList;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import java.awt.Color;

public class Game extends JFrame {

	private JPanel contentPane;
	private JTextField gameChatTxtField;
	private String[] activePlayers;
	private Timer timer;
	private char[][] board;
	private boolean validMove;
	private boolean winCondition;
	private boolean draw;
	private char colour  = ' ';
	/**
	 * Create the frame.
	 * 
	 * @param client
	 */
	
	public Game(Client client) {
		
		
		GameBoard gameBoard = new GameBoard();
		this.board = gameBoard.getBoard();
		GameLogic logic = new GameLogic();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 996, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btn32 = new JButton("");
		btn32.setBounds(232, 240, 82, 48);
		contentPane.add(btn32);

		JButton btn33 = new JButton("");
		btn33.setBounds(324, 240, 80, 48);
		contentPane.add(btn33);

		JButton btn51 = new JButton("");
		btn51.setBounds(133, 372, 89, 48);
		contentPane.add(btn51);

		JButton btn52 = new JButton("");
		btn52.setBounds(232, 372, 82, 48);
		contentPane.add(btn52);

		JButton btn42 = new JButton("");
		btn42.setBounds(232, 305, 82, 48);
		contentPane.add(btn42);

		JButton btn43 = new JButton("");
		btn43.setBounds(324, 305, 80, 48);
		contentPane.add(btn43);

		JButton btn41 = new JButton("");
		btn41.setBounds(133, 305, 89, 48);
		contentPane.add(btn41);

		JButton btn22 = new JButton("");
		btn22.setBounds(232, 168, 82, 48);
		contentPane.add(btn22);

		JButton btn50 = new JButton("");
		btn50.setBounds(33, 372, 89, 48);
		contentPane.add(btn50);

		JButton btn23 = new JButton("");
		btn23.setBounds(324, 168, 80, 48);
		contentPane.add(btn23);

		JButton btn31 = new JButton("");
		btn31.setBounds(133, 240, 89, 48);
		contentPane.add(btn31);

		JButton btn30 = new JButton("");
		btn30.setBounds(33, 240, 89, 48);
		contentPane.add(btn30);

		JButton btn40 = new JButton("");
		btn40.setBounds(33, 305, 89, 48);
		contentPane.add(btn40);

		JButton btn53 = new JButton("");
		btn53.setBounds(324, 372, 80, 48);
		contentPane.add(btn53);

		JButton btn21 = new JButton("");
		btn21.setBounds(133, 168, 89, 48);
		contentPane.add(btn21);

		JButton btn20 = new JButton("");
		btn20.setBounds(33, 168, 89, 48);
		contentPane.add(btn20);

		JButton btn56 = new JButton("");
		btn56.setBounds(616, 372, 89, 48);
		contentPane.add(btn56);

		JButton btn46 = new JButton("");
		btn46.setBounds(616, 305, 89, 48);
		contentPane.add(btn46);

		JButton btn54 = new JButton("");
		btn54.setBounds(421, 372, 82, 48);
		contentPane.add(btn54);

		JButton btn25 = new JButton("");
		btn25.setBounds(519, 168, 83, 48);
		contentPane.add(btn25);

		JButton btn34 = new JButton("");
		btn34.setBounds(421, 240, 82, 48);
		contentPane.add(btn34);

		JButton btn35 = new JButton("");
		btn35.setBounds(519, 240, 83, 48);
		contentPane.add(btn35);

		JButton btn36 = new JButton("");
		btn36.setBounds(616, 240, 89, 48);
		contentPane.add(btn36);

		JButton btn75 = new JButton("");
		btn75.setBounds(519, 499, 83, 48);
		contentPane.add(btn75);
		
		JButton btn44 = new JButton("");
		btn44.setBounds(421, 305, 82, 48);
		contentPane.add(btn44);

		JButton btn76 = new JButton("");
		btn76.setBounds(616, 499, 89, 48);
		contentPane.add(btn76);

		JButton btn24 = new JButton("");
		btn24.setBounds(421, 168, 82, 48);
		contentPane.add(btn24);
		
		JButton btn45 = new JButton("");
		btn45.setBounds(519, 305, 83, 48);
		contentPane.add(btn45);

		JButton btn26 = new JButton("");
		btn26.setBounds(616, 168, 89, 48);
		contentPane.add(btn26);

		JButton btn55 = new JButton("");
		btn55.setBounds(519, 372, 83, 48);
		contentPane.add(btn55);

		JButton btn74 = new JButton("");
		btn74.setBounds(421, 499, 82, 48);
		contentPane.add(btn74);

		JButton btn72 = new JButton("");
		btn72.setBounds(232, 499, 82, 48);
		contentPane.add(btn72);

		JButton btn73 = new JButton("");
		btn73.setBounds(324, 499, 82, 48);
		contentPane.add(btn73);

		JButton btn64 = new JButton("");
		btn64.setBounds(421, 436, 82, 48);
		contentPane.add(btn64);

		JButton btn65 = new JButton("");
		btn65.setBounds(519, 436, 83, 48);
		contentPane.add(btn65);

		JButton btn66 = new JButton("");
		btn66.setBounds(616, 436, 89, 48);
		contentPane.add(btn66);

		JButton btn71 = new JButton("");
		btn71.setBounds(132, 499, 89, 48);
		contentPane.add(btn71);

		JButton btn62 = new JButton("");
		btn62.setBounds(232, 436, 82, 48);
		contentPane.add(btn62);

		JButton btn63 = new JButton("");
		btn63.setBounds(324, 436, 80, 48);
		contentPane.add(btn63);

		JButton btn61 = new JButton("");
		btn61.setBounds(133, 436, 89, 48);
		contentPane.add(btn61);

		JButton btn60 = new JButton("");
		btn60.setBounds(33, 436, 89, 48);
		contentPane.add(btn60);

		JButton btn70 = new JButton("");
		btn70.setBounds(33, 499, 89, 48);
		contentPane.add(btn70);

		JScrollPane gameChatHistoryTxtPane = new JScrollPane();
		gameChatHistoryTxtPane.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		gameChatHistoryTxtPane.setBounds(21, 574, 949, 85);
		contentPane.add(gameChatHistoryTxtPane);

		DefaultListModel<String> chatHistory = new DefaultListModel<String>();

		JList<String> chatHistoryList = new JList<String>(chatHistory);
		gameChatHistoryTxtPane.setViewportView(chatHistoryList);

		gameChatTxtField = new JTextField();
		gameChatTxtField.setFont(new Font("Roboto Condensed", Font.PLAIN, 12));
		gameChatTxtField.setBounds(21, 670, 887, 27);
		contentPane.add(gameChatTxtField);
		gameChatTxtField.setColumns(10);

		JButton btnGameChatSend = new JButton(">");
		btnGameChatSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// pull the information from gameChatTxtField and send it to the
				// server.
				String message = gameChatTxtField.getText();
				gameChatTxtField.setText(null);
				try {
					client.gameMessage(message);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnGameChatSend.setFont(new Font("Roboto Condensed", Font.PLAIN, 11));
		btnGameChatSend.setBounds(920, 670, 50, 27);
		contentPane.add(btnGameChatSend);

		JScrollPane playerTxtPane = new JScrollPane();
		playerTxtPane.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		playerTxtPane.setBounds(820, 26, 150, 529);
		contentPane.add(playerTxtPane);

		DefaultListModel<String> players = new DefaultListModel<String>();

		JList<String> gamePlayerList = new JList<String>(players);
		playerTxtPane.setViewportView(gamePlayerList);

		JButton btnCol0 = new JButton("");
		btnCol0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 1 was selected
				// once the server tells you what button is needed, you have to
				// change the icon. like so.
				activePlayers = client.getRoomPlayers();
	
				if(board[5][0] == '.'){
					board[5][0] = 'R';
					//btn70.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
					
				}
				else if(board[4][0] == '.'){
					board[4][0] = 'R';
					//btn60.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][0] == '.'){
					board[3][0] = 'R';
					//btn50.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][0] == '.'){
					board[2][0] = 'R';
					//btn40.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][0] == '.'){
					board[1][0] = 'R';
					//btn30.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][0] == '.'){
					board[0][0] = 'R';
					//btn20.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol0.setBounds(62, 48, 39, 23);
		contentPane.add(btnCol0);

		JButton btnCol1 = new JButton("");
		btnCol1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 2 was selected

				// this is sample code on how to set the icon backgrounds.
				// Remove this.
				if(board[5][1] == '.'){
					board[5][1] = 'R';
					btn71.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][1] == '.'){
					board[4][1] = 'R';
					btn61.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][1] == '.'){
					board[3][1] = 'R';
					btn51.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][1] == '.'){
					board[2][1] = 'R';
					btn41.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][1] == '.'){
					board[1][1] = 'R';
					btn31.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][1] == '.'){
					board[0][1] = 'R';
					btn21.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				//btn71.setIcon(new ImageIcon(Game.class.getResource("/resources/BluePiece.png")));
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/

			}
		});
		btnCol1.setBounds(157, 48, 39, 23);
		contentPane.add(btnCol1);

		JButton btnCol2 = new JButton("");
		btnCol2.addActionListener(new ActionListener() {
			//TODO: column 3
			public void actionPerformed(ActionEvent e) {
				if(board[5][2] == '.'){
					board[5][2] = 'R';
					btn72.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][2] == '.'){
					board[4][2] = 'R';
					btn62.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][2] == '.'){
					board[3][2] = 'R';
					btn52.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][2] == '.'){
					board[2][2] = 'R';
					btn42.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][2] == '.'){
					board[1][2] = 'R';
					btn32.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][2] == '.'){
					board[0][2] = 'R';
					btn22.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol2.setBounds(249, 48, 39, 23);
		contentPane.add(btnCol2);

		JButton btnCol3 = new JButton("");
		btnCol3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: column 4
				if(board[5][3] == '.'){
					board[5][3] = 'R';
					btn73.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][3] == '.'){
					board[4][3] = 'R';
					btn63.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][3] == '.'){
					board[3][3] = 'R';
					btn53.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][3] == '.'){
					board[2][3] = 'R';
					btn43.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][3] == '.'){
					board[1][3] = 'R';
					btn33.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][3] == '.'){
					board[0][3] = 'R';
					btn23.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol3.setBounds(345, 48, 39, 23);
		contentPane.add(btnCol3);

		JButton btnCol4 = new JButton("");
		btnCol4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: column 5
				if(board[5][4] == '.'){
					board[5][4] = 'R';
					btn74.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][4] == '.'){
					board[4][4] = 'R';
					btn64.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][4] == '.'){
					board[3][4] = 'R';
					btn54.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][4] == '.'){
					board[2][4] = 'R';
					btn44.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][4] == '.'){
					board[1][4] = 'R';
					btn34.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][4] == '.'){
					board[0][4] = 'R';
					btn24.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol4.setBounds(432, 48, 39, 23);
		contentPane.add(btnCol4);

		JButton btnCol5 = new JButton("");
		btnCol5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: column 6
				if(board[5][5] == '.'){
					board[5][5] = 'R';
					btn75.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][5] == '.'){
					board[4][5] = 'R';
					btn65.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][5] == '.'){
					board[3][5] = 'R';
					btn55.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][5] == '.'){
					board[2][5] = 'R';
					btn45.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][5] == '.'){
					board[1][5] = 'R';
					btn35.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][5] == '.'){
					board[0][5] = 'R';
					btn25.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol5.setBounds(531, 48, 39, 23);
		contentPane.add(btnCol5);

		JButton btnCol6 = new JButton("");
		btnCol6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: column 7
				if(board[5][6] == '.'){
					board[5][6] = 'R';
					btn76.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[4][6] == '.'){
					board[4][6] = 'R';
					btn66.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[3][6] == '.'){
					board[3][6] = 'R';
					btn56.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[2][6] == '.'){
					board[2][6] = 'R';
					btn46.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[1][6] == '.'){
					board[1][6] = 'R';
					btn36.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}
				else if(board[0][6] == '.'){
					board[0][6] = 'R';
					btn26.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
					
				}	
				/*
				if(logic.checkWin(board, 'R'))
					//stop game
				else if(logic.checkDraw(board))
					//stop game
				*/
			}
		});
		btnCol6.setBounds(640, 48, 39, 23);
		contentPane.add(btnCol6);

		/*JButton btnGameBoard = new JButton();
		btnGameBoard.setEnabled(false);
		btnGameBoard.setBackground(new Color(255, 255, 255));
		btnGameBoard.setIcon(new ImageIcon(Game.class.getResource("/resources/finalboard.png")));
		btnGameBoard.setBounds(21, 82, 7ss89, 372);
		contentPane.add(btnGameBoard);*/

		//TODO: We gonna need to update the board here also its gonna be a pain
		timer = new Timer(2500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// if all the panes we are refreshing are not empty clear them

				if (!players.isEmpty())
					players.clear();
				if (!chatHistory.isEmpty())
					chatHistory.clear();
				try {
					// populate the game rooms list frame. * DONE*
					// same way you did it for players
					client.refreshGame();
					String[] playerList = client.getRoomPlayers();
					String[] spectatorList = client.getSpectators();
					String[] gameMessagesList = client.getGameMessages();
					for (int i = 0; i < playerList.length; i++)
						players.addElement(playerList[i]);
					for (int i = 0; i < spectatorList.length; i++)
						players.addElement(spectatorList[i]);
					for (int i = 0; i < gameMessagesList.length; i++)
						chatHistory.addElement(gameMessagesList[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		timer.start();

		// what happens when you hit close window
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				try {
					timer.stop();
					client.leaveGame();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
