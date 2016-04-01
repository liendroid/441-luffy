package gui;

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

	/**
	 * Create the frame.
	 * 
	 * @param client
	 */
	public Game(Client client) {
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

		JButton btn12 = new JButton("");
		btn12.setBounds(232, 109, 82, 48);
		contentPane.add(btn12);

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

		JButton btn11 = new JButton("");
		btn11.setBounds(133, 109, 89, 48);
		contentPane.add(btn11);

		JButton btn30 = new JButton("");
		btn30.setBounds(33, 240, 89, 48);
		contentPane.add(btn30);

		JButton btn13 = new JButton("");
		btn13.setBounds(324, 109, 80, 48);
		contentPane.add(btn13);

		JButton btn40 = new JButton("");
		btn40.setBounds(33, 305, 89, 48);
		contentPane.add(btn40);

		JButton btn10 = new JButton("");
		btn10.setBounds(33, 109, 89, 48);
		contentPane.add(btn10);

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

		JButton btn77 = new JButton("");
		btn77.setBounds(715, 499, 80, 48);
		contentPane.add(btn77);

		JButton btn47 = new JButton("");
		btn47.setBounds(715, 305, 80, 48);
		contentPane.add(btn47);

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

		JButton btn17 = new JButton("");
		btn17.setBounds(715, 109, 80, 48);
		contentPane.add(btn17);

		JButton btn75 = new JButton("");
		btn75.setBounds(519, 499, 83, 48);
		contentPane.add(btn75);

		JButton btn15 = new JButton("");
		btn15.setBounds(519, 109, 83, 48);
		contentPane.add(btn15);

		JButton btn44 = new JButton("");
		btn44.setBounds(421, 305, 82, 48);
		contentPane.add(btn44);

		JButton btn76 = new JButton("");
		btn76.setBounds(616, 499, 89, 48);
		contentPane.add(btn76);

		JButton btn24 = new JButton("");
		btn24.setBounds(421, 168, 82, 48);
		contentPane.add(btn24);

		JButton btn16 = new JButton("");
		btn16.setBounds(616, 109, 89, 48);
		contentPane.add(btn16);

		JButton btn27 = new JButton("");
		btn27.setBounds(715, 168, 80, 48);
		contentPane.add(btn27);

		JButton btn45 = new JButton("");
		btn45.setBounds(519, 305, 83, 48);
		contentPane.add(btn45);

		JButton btn26 = new JButton("");
		btn26.setBounds(616, 168, 89, 48);
		contentPane.add(btn26);

		JButton btn57 = new JButton("");
		btn57.setBounds(715, 372, 80, 48);
		contentPane.add(btn57);

		JButton btn55 = new JButton("");
		btn55.setBounds(519, 372, 83, 48);
		contentPane.add(btn55);

		JButton btn74 = new JButton("");
		btn74.setBounds(421, 499, 82, 48);
		contentPane.add(btn74);

		JButton btn37 = new JButton("");
		btn37.setBounds(715, 240, 80, 48);
		contentPane.add(btn37);

		JButton btn14 = new JButton("");
		btn14.setBounds(421, 109, 82, 48);
		contentPane.add(btn14);

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

		JButton btn67 = new JButton("");
		btn67.setBounds(715, 436, 80, 48);
		contentPane.add(btn67);

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
		// btn11.setIcon(new
		// ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
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
				btn70.setIcon(new ImageIcon(Game.class.getResource("/resources/RedPiece.png")));
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
				if (btn71.getIcon() != null) {
					btn71.setIcon(null);
				}
				btn71.setIcon(new ImageIcon(Game.class.getResource("/resources/BluePiece.png")));

			}
		});
		btnCol1.setBounds(157, 48, 39, 23);
		contentPane.add(btnCol1);

		JButton btnCol2 = new JButton("");
		btnCol2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 3 was selected
			}
		});
		btnCol2.setBounds(249, 48, 39, 23);
		contentPane.add(btnCol2);

		JButton btnCol3 = new JButton("");
		btnCol3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 4 was selected
			}
		});
		btnCol3.setBounds(345, 48, 39, 23);
		contentPane.add(btnCol3);

		JButton btnCol4 = new JButton("");
		btnCol4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 5 was selected
			}
		});
		btnCol4.setBounds(432, 48, 39, 23);
		contentPane.add(btnCol4);

		JButton btnCol5 = new JButton("");
		btnCol5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 6 was selected
			}
		});
		btnCol5.setBounds(531, 48, 39, 23);
		contentPane.add(btnCol5);

		JButton btnCol6 = new JButton("");
		btnCol6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 7 was selected
			}
		});
		btnCol6.setBounds(640, 48, 39, 23);
		contentPane.add(btnCol6);

		JButton btnCol7 = new JButton("");
		btnCol7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Tell the server Column 8 was selected
			}
		});
		btnCol7.setBounds(736, 48, 39, 23);
		contentPane.add(btnCol7);

		JButton btnGameBoard = new JButton();
		btnGameBoard.setEnabled(false);
		btnGameBoard.setBackground(new Color(255, 255, 255));
		btnGameBoard.setIcon(new ImageIcon(Game.class.getResource("/resources/finalboard.png")));
		btnGameBoard.setBounds(21, 82, 789, 472);
		contentPane.add(btnGameBoard);

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
