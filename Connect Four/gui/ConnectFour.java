package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.List;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;

import network.TestClientForDan;
import network.Client;
import javax.swing.JScrollPane;

public class ConnectFour extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxtfield;
	private JPasswordField passwordTxtfield;
	private JLabel lblSignUp;
	private JTextField chatTxtField;
	private Client client = null;
	private JTextField IPTxtField;
	private JTextField portNumberTxtField;
	
	private JList<String> currentPlayers;
	private JList<String> gamesLobbyList;
	private DefaultListModel<String> players;
	
	private String playerName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConnectFour frame = new ConnectFour();
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
	public ConnectFour() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 996, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		initMainMenu();
		initGameLobby();
		
	}
	public void initMainMenu()
	{
		JPanel mainMenuPanel = new JPanel();
		contentPane.add(mainMenuPanel, "mainMenuCard");
		mainMenuPanel.setLayout(null);
		
		JLabel lblConnectFour = new JLabel("Connect Four");
		lblConnectFour.setForeground(new Color(0, 102, 204));
		lblConnectFour.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 60));
		lblConnectFour.setBounds(353, 68, 271, 55);
		mainMenuPanel.add(lblConnectFour);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(51, 153, 204));
		lblUsername.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblUsername.setBounds(67, 191, 141, 41);
		mainMenuPanel.add(lblUsername);
		
		usernameTxtfield = new JTextField();
		usernameTxtfield.setFont(new Font("Lato", Font.PLAIN, 20));
		usernameTxtfield.setBounds(67, 233, 238, 35);
		mainMenuPanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(51, 153, 204));
		lblPassword.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblPassword.setBounds(67, 279, 141, 41);
		mainMenuPanel.add(lblPassword);
		
		passwordTxtfield = new JPasswordField();
		passwordTxtfield.setBounds(67, 324, 237, 35);
		mainMenuPanel.add(passwordTxtfield);
		passwordTxtfield.setColumns(10);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchCards();
			}
		});
		btnLogin.setFont(new Font("Roboto Condensed", Font.PLAIN, 17));
		btnLogin.setBounds(205, 370, 100, 35);
		mainMenuPanel.add(btnLogin);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switchCards();
			}
		});
		lblSignUp.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		lblSignUp.setBounds(77, 375, 74, 22);
		mainMenuPanel.add(lblSignUp);
		
		JLabel lblIpaddress = new JLabel("IPAddress");
		lblIpaddress.setBounds(550, 208, 109, 22);
		mainMenuPanel.add(lblIpaddress);
		
		IPTxtField = new JTextField();
		IPTxtField.setBounds(550, 233, 238, 35);
		mainMenuPanel.add(IPTxtField);
		IPTxtField.setColumns(10);
		
		JLabel lblPortNumber = new JLabel("Port Number");
		lblPortNumber.setBounds(550, 297, 74, 20);
		mainMenuPanel.add(lblPortNumber);
		
		portNumberTxtField = new JTextField();
		portNumberTxtField.setBounds(550, 324, 238, 35);
		mainMenuPanel.add(portNumberTxtField);
		portNumberTxtField.setColumns(10);
	}
	
	public void initGameLobby()
	{
		
		JPanel gameLobbyPanel = new JPanel();
		contentPane.add(gameLobbyPanel, "gameLobbyCard");
		gameLobbyPanel.setLayout(null);
		
		JLabel lblGames = new JLabel("Games");
		lblGames.setForeground(new Color(0, 102, 204));
		lblGames.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 40));
		lblGames.setBounds(46, 25, 102, 33);
		gameLobbyPanel.add(lblGames);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Game newGame = new Game();
					newGame.setVisible(true);
					client.createGame(playerName);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnCreate.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnCreate.setBounds(422, 447, 102, 33);
		gameLobbyPanel.add(btnCreate);
		
		JButton btnJoinGame = new JButton("Join");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game joinGame = new Game();
				joinGame.setVisible(true);
			}
		});
		btnJoinGame.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnJoinGame.setBounds(534, 448, 103, 31);
		gameLobbyPanel.add(btnJoinGame);
		
		JTextPane chatHistoryTxtPane = new JTextPane();
		chatHistoryTxtPane.setFont(new Font("Roboto Condensed", Font.PLAIN, 12));
		chatHistoryTxtPane.setEditable(false);
		chatHistoryTxtPane.setBounds(44, 489, 916, 140);
		gameLobbyPanel.add(chatHistoryTxtPane);
		
		chatTxtField = new JTextField();
		chatTxtField.setBounds(44, 641, 851, 41);
		gameLobbyPanel.add(chatTxtField);
		chatTxtField.setColumns(10);
		
		JButton btnSend = new JButton(">");
		btnSend.setBounds(905, 644, 55, 38);
		gameLobbyPanel.add(btnSend);
		
		JScrollPane playerScrollPane = new JScrollPane();
		playerScrollPane.setBounds(761, 69, 199, 374);
		gameLobbyPanel.add(playerScrollPane);
		
		DefaultListModel<String> players = new DefaultListModel<String>();
		players.addElement("");
		players.addElement("");
		JList<String> currentPlayers = new JList<String>(players);
		playerScrollPane.setViewportView(currentPlayers);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!players.isEmpty())
				{
					players.clear();
				}
				//parse server players here and add to list
				try {
					String[] playerList = client.refresh();
					for(int i = 0; i < playerList.length; i++)
						players.addElement(playerList[i]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnRefresh.setBounds(865, 450, 95, 33);
		btnRefresh.setFont(new Font("Roboto Condensed", Font.PLAIN, 12));
		gameLobbyPanel.add(btnRefresh);
		
		JButton btnSpectate = new JButton("Spectate");
		btnSpectate.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnSpectate.setBounds(648, 448, 103, 31);
		gameLobbyPanel.add(btnSpectate);
		
		JScrollPane currentGamesScrollPane = new JScrollPane();
		currentGamesScrollPane.setBounds(45, 69, 706, 374);
		gameLobbyPanel.add(currentGamesScrollPane);
		
		JList<String> gamesLobbyList = new JList<String>();
		currentGamesScrollPane.setViewportView(gamesLobbyList);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logout();
			}
		});
		btnLogOut.setBounds(871, 25, 89, 33);
		gameLobbyPanel.add(btnLogOut);
	}
	
	public void switchCards()
	{
		boolean userEmpty = usernameTxtfield.getText().isEmpty();
		char[] pw = passwordTxtfield.getPassword();
		String fullPw = new String(pw);
		boolean pwEmpty = fullPw.isEmpty();
		String ipAddress = IPTxtField.getText();
		boolean ipEmpty = ipAddress.isEmpty();
		boolean portEmpty = portNumberTxtField.getText().isEmpty();
		if(!userEmpty && !pwEmpty && !ipEmpty && !portEmpty)
		{
			int portNumber = Integer.parseInt(portNumberTxtField.getText());
			try {
					client = new Client(ipAddress, portNumber);
					client.login();
					
					playerName = usernameTxtfield.getText();
					
					CardLayout cLayout = (CardLayout) contentPane.getLayout();
					
					//populate the the games and current players 
					cLayout.show(contentPane, "gameLobbyCard");
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
		else if(userEmpty)
		{
			JOptionPane.showMessageDialog(null, "Username field cannot be empty");
		}
		else if(pwEmpty)
		{
			JOptionPane.showMessageDialog(null, "Password field cannot be empty");
		}
		else if(ipEmpty)
		{
			JOptionPane.showMessageDialog(null, "IP Address field cannot be empty");
		}
		else if(portEmpty)
		{
			JOptionPane.showMessageDialog(null, "Port Number field cannot be empty");
		}
		else {
			JOptionPane.showMessageDialog(null, "Well this is embarrassing. I don't know what is wrong. Try again please. D:");
		}
	}
	
	public void populatePlayers()
	{
	}
	
	public void logout()
	{
		try {
			client.logout();
			
			CardLayout cLayout = (CardLayout) contentPane.getLayout();
			//populate the the games and current players 
			cLayout.show(contentPane, "mainMenuCard");
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
