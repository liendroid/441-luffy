package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.List;
import java.lang.reflect.Array;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

	private JPanel contentPane;
	private JTextField gameChatTxtField;
	private String[] activePlayers;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String[] activePlayers = new String[10];
					activePlayers[0] = "bah";
					activePlayers[1] = "BossMan";
					
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 996, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(21, 82, 789, 473);
		contentPane.add(gamePanel);
		
		JTextPane gameChatHistoryTxtPane = new JTextPane();
		gameChatHistoryTxtPane.setFont(new Font("Roboto Condensed", Font.PLAIN, 14));
		gameChatHistoryTxtPane.setEditable(false);
		gameChatHistoryTxtPane.setBounds(21, 574, 949, 85);
		contentPane.add(gameChatHistoryTxtPane);
		
		gameChatTxtField = new JTextField();
		gameChatTxtField.setFont(new Font("Roboto Condensed", Font.PLAIN, 12));
		gameChatTxtField.setBounds(21, 670, 887, 27);
		contentPane.add(gameChatTxtField);
		gameChatTxtField.setColumns(10);
		
		JButton btnGameChatSend = new JButton(">");
		btnGameChatSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Send the server chat info. You got the drill. 
				//pull the information from gameChatTxtField and send it to the server. 
			}
		});
		btnGameChatSend.setFont(new Font("Roboto Condensed", Font.PLAIN, 11));
		btnGameChatSend.setBounds(920, 670, 50, 27);
		contentPane.add(btnGameChatSend);
		DefaultListModel<String> players = new DefaultListModel<String>();
		players.addElement("");
		players.addElement("");
		
		//TODO: This is where you populate the list with players and spectators.
		//You should call the info from the server. 
		JList<String> gamePlayerList = new JList<String>(players);
		gamePlayerList.setBounds(820, 26, 150, 529);
		contentPane.add(gamePlayerList);
		
		JButton btnCol1 = new JButton("");
		btnCol1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 1 was selected
			}
		});
		btnCol1.setBounds(33, 48, 39, 23);
		contentPane.add(btnCol1);
		
		JButton btnCol2 = new JButton("");
		btnCol2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 2 was selected
			}
		});
		btnCol2.setBounds(125, 48, 39, 23);
		contentPane.add(btnCol2);
		
		JButton btnCol3 = new JButton("");
		btnCol3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 3 was selected
			}
		});
		btnCol3.setBounds(220, 48, 39, 23);
		contentPane.add(btnCol3);
		
		JButton btnCol4 = new JButton("");
		btnCol4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 4 was selected
			}
		});
		btnCol4.setBounds(315, 48, 39, 23);
		contentPane.add(btnCol4);
		
		JButton btnCol5 = new JButton("");
		btnCol5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 5 was selected
			}
		});
		btnCol5.setBounds(414, 48, 39, 23);
		contentPane.add(btnCol5);
		
		JButton btnCol6 = new JButton("");
		btnCol6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 6 was selected
			}
		});
		btnCol6.setBounds(519, 48, 39, 23);
		contentPane.add(btnCol6);
		
		JButton btnCol7 = new JButton("");
		btnCol7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 7 was selected
			}
		});
		btnCol7.setBounds(626, 48, 39, 23);
		contentPane.add(btnCol7);
		
		JButton btnCol8 = new JButton("");
		btnCol8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: Tell the server Column 8 was selected
			}
		});
		btnCol8.setBounds(744, 48, 39, 23);
		contentPane.add(btnCol8);
	}
}
