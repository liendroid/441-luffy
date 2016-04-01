package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.io.IOException;
import java.net.Socket;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataListener;

import network.TestClientForDan;
import network.User;
import network.UserDB;
import network.Client;
import javax.swing.JScrollPane;
import java.awt.event.WindowAdapter;

public class ConnectFour extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxtfield;
	private JPasswordField passwordTxtfield;
	private JTextField chatTxtField;
	private Client client = null;
	private JTextField IPTxtField;
	private JTextField portNumberTxtField;

	private JList<String> currentPlayers;
	private JList<String> gamesLobbyList;
	private DefaultListModel<String> players;
	private Timer timer;
	private UserDB uDB = new UserDB();

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
		uDB = new UserDB();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 996, 742);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		initMainMenu();
		initGameLobby();
	
	}

	public void initMainMenu() {
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

		JLabel lblIpaddress = new JLabel("IPAddress");
		lblIpaddress.setForeground(new Color(51, 153, 204));
		lblIpaddress.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblIpaddress.setBounds(550, 200, 124, 28);
		mainMenuPanel.add(lblIpaddress);

		IPTxtField = new JTextField();
		IPTxtField.setBounds(550, 233, 238, 35);
		mainMenuPanel.add(IPTxtField);
		IPTxtField.setColumns(10);

		JLabel lblPortNumber = new JLabel("Port Number");
		lblPortNumber.setForeground(new Color(51, 153, 204));
		lblPortNumber.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblPortNumber.setBounds(550, 289, 149, 28);
		mainMenuPanel.add(lblPortNumber);

		portNumberTxtField = new JTextField();
		portNumberTxtField.setBounds(550, 324, 238, 35);
		mainMenuPanel.add(portNumberTxtField);
		portNumberTxtField.setColumns(10);
	}

	public void initGameLobby() {

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
					// timer.stop();
					Game newGame = new Game(client);
					newGame.setVisible(true);
					client.createGame(playerName);
				} catch (IOException e) {
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
				while (true) {
					String path = JOptionPane.showInputDialog("which room?");
					int choice = Integer.parseInt(path);
					// System.out.println(choice);
					String[] rooms = client.getRooms();
					if (choice <= rooms.length) {// room exists
						String room = rooms[choice - 1];
						// System.out.println(room);
						if (room.contains("Joinable")) { // room is joinable
							try {
								client.joinGame(choice);
								break;
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				// timer.stop();
				Game joinGame = new Game(client);
				joinGame.setVisible(true);
			}
		});
		btnJoinGame.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnJoinGame.setBounds(534, 448, 103, 31);
		gameLobbyPanel.add(btnJoinGame);

		JScrollPane chatHistoryTxtPane = new JScrollPane();
		chatHistoryTxtPane.setFont(new Font("Roboto Condensed", Font.PLAIN, 12));
		// chatHistoryTxtPane.setEditable(false);
		chatHistoryTxtPane.setBounds(44, 489, 916, 140);
		gameLobbyPanel.add(chatHistoryTxtPane);

		DefaultListModel<String> chatHistory = new DefaultListModel<String>();
		JList<String> chatHistoryList = new JList<String>(chatHistory);
		chatHistoryTxtPane.setViewportView(chatHistoryList);

		chatTxtField = new JTextField();
		chatTxtField.setBounds(44, 641, 851, 41);
		gameLobbyPanel.add(chatTxtField);
		chatTxtField.setColumns(10);

		JButton btnSend = new JButton(">");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Send chat message to the server
				// Grab the message content from chatTxtField and send it to the
				// server.
				String message = chatTxtField.getText();
				chatTxtField.setText(null);
				try {
					client.serverMessage(message);
					// You need to call an update to the chatHistoryTxtPane
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnSend.setBounds(905, 644, 55, 38);
		gameLobbyPanel.add(btnSend);

		JScrollPane playerScrollPane = new JScrollPane();
		playerScrollPane.setBounds(761, 69, 199, 374);
		gameLobbyPanel.add(playerScrollPane);

		DefaultListModel<String> players = new DefaultListModel<String>();
		JList<String> currentPlayers = new JList<String>(players);
		playerScrollPane.setViewportView(currentPlayers);

		JScrollPane currentGamesScrollPane = new JScrollPane();
		currentGamesScrollPane.setBounds(45, 69, 706, 374);
		gameLobbyPanel.add(currentGamesScrollPane);

		DefaultListModel<String> games = new DefaultListModel<String>();
		JList<String> gamesLobbyList = new JList<String>(games);
		currentGamesScrollPane.setViewportView(gamesLobbyList);

		// JButton btnRefresh = new JButton("Refresh");
		// btnRefresh.addActionListener(new ActionListener() {
		// refresh timer
		timer = new Timer(2500, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!players.isEmpty())
					players.clear();
				if (!games.isEmpty())
					games.clear();
				if (!chatHistory.isEmpty())
					chatHistory.clear();
				try {
					// populate the game rooms list frame. * DONE*
					// same way you did it for players
					client.refresh();
					String[] playerList = client.getPlayers();
					String[] roomList = client.getRooms();
					String[] serverMessagesList = client.getServerMessages();
					for (int i = 0; i < playerList.length; i++)
						players.addElement(playerList[i]);
					for (int i = 0; i < roomList.length; i++)
						games.addElement(roomList[i]);
					for (int i = 0; i < serverMessagesList.length; i++)
						chatHistory.addElement(serverMessagesList[i]);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		// btnRefresh.setBounds(865, 450, 95, 33);
		// btnRefresh.setFont(new Font("Roboto Condensed", Font.PLAIN, 15));
		// gameLobbyPanel.add(btnRefresh);

		JButton btnSpectate = new JButton("Spectate");
		btnSpectate.setFont(new Font("Roboto Condensed", Font.PLAIN, 18));
		btnSpectate.setBounds(648, 448, 103, 31);
		gameLobbyPanel.add(btnSpectate);

		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setFont(new Font("Roboto Condensed", Font.PLAIN, 15));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				logout();
			}
		});
		btnLogOut.setBounds(871, 25, 89, 33);
		gameLobbyPanel.add(btnLogOut);
		// what happens when you hit close window
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					timer.stop();
					logout();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				System.exit(-1);

			}
		});

	}

	public boolean login(String username, String password)
	{
		User[] users = uDB.getUserDB();
		for(int i = 0; i < users.length; i++)
		{
			String user = users[i].getUsername();
			String pw = users[i].getPassword();
			if(user.equals(username) && pw.equals(password))
			{
				return true;
			}
		}
		return false;
	}
	
	public void switchCards() {
		boolean userEmpty = usernameTxtfield.getText().isEmpty();
		char[] pw = passwordTxtfield.getPassword();
		String fullPw = new String(pw);
		boolean pwEmpty = fullPw.isEmpty();
		String ipAddress = IPTxtField.getText();
		boolean ipEmpty = ipAddress.isEmpty();
		boolean portEmpty = portNumberTxtField.getText().isEmpty();
		if (!userEmpty && !pwEmpty && !ipEmpty && !portEmpty) {
			int portNumber = Integer.parseInt(portNumberTxtField.getText());
			try {
				
				boolean loginSuccess = login(usernameTxtfield.getText(), fullPw);
				if(loginSuccess)
				{
					
					playerName = usernameTxtfield.getText();
					
					client = new Client(ipAddress, portNumber);
					client.login(playerName);

					CardLayout cLayout = (CardLayout) contentPane.getLayout();

					// populate the the games and current players
					cLayout.show(contentPane, "gameLobbyCard");
					timer.start();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Login failed.");
				}
				
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		} else if (userEmpty) {
			JOptionPane.showMessageDialog(null, "Username field cannot be empty");
		} else if (pwEmpty) {
			JOptionPane.showMessageDialog(null, "Password field cannot be empty");
		} else if (ipEmpty) {
			JOptionPane.showMessageDialog(null, "IP Address field cannot be empty");
		} else if (portEmpty) {
			JOptionPane.showMessageDialog(null, "Port Number field cannot be empty");
		} else {
			JOptionPane.showMessageDialog(null,
					"Well this is embarrassing. I don't know what is wrong. Try again please. D:");
		}
	}

	public void logout() {
		try {
			client.logout();

			CardLayout cLayout = (CardLayout) contentPane.getLayout();
			// populate the the games and current players
			cLayout.show(contentPane, "mainMenuCard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
