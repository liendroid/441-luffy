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
import java.awt.Graphics;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.lang.reflect.Array;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
import java.awt.Canvas;

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
		
		//Canvas canvas = new GameCanvas();
		canvas.setBounds(21, 78, 791, 477);
		contentPane.add(canvas);
	}
	
	/*public class GameCanvas extends Canvas
	{
		Image image;
		
		public public GameCanvas() {
			image = Toolkit.getDefaultToolkit().getImage("/resources/ConnectFourBoard.png");
		}
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
		}
	}*/
}
