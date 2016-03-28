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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import network.Client;

public class ConnectFour extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxtfield;
	private JPasswordField passwordTxtfield;
	private JLabel lblSignUp;
	private JTextField chatTxtField;
	//private Client client;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Client client = new Client();
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
		
		JPanel mainMenuPanel = new JPanel();
		contentPane.add(mainMenuPanel, "mainMenuCard");
		mainMenuPanel.setLayout(null);
		
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
				Game newGame = new Game();
				newGame.setVisible(true);
			}
		});
		btnCreate.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnCreate.setBounds(536, 448, 102, 33);
		gameLobbyPanel.add(btnCreate);
		
		JList list = new JList();
		list.setBounds(761, 62, 199, 375);
		gameLobbyPanel.add(list);
		
		JButton btnJoinGame = new JButton("Join");
		btnJoinGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game joinGame = new Game();
				joinGame.setVisible(true);
			}
		});
		btnJoinGame.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnJoinGame.setBounds(648, 448, 103, 31);
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
		
		JList gamesLobbyList = new JList();
		gamesLobbyList.setBounds(46, 62, 705, 374);
		gameLobbyPanel.add(gamesLobbyList);
		
		JLabel lblConnectFour = new JLabel("Connect Four");
		lblConnectFour.setForeground(new Color(0, 102, 204));
		lblConnectFour.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 60));
		lblConnectFour.setBounds(353, 68, 271, 55);
		mainMenuPanel.add(lblConnectFour);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(new Color(51, 153, 204));
		lblUsername.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblUsername.setBounds(52, 199, 141, 41);
		mainMenuPanel.add(lblUsername);
		
		usernameTxtfield = new JTextField();
		usernameTxtfield.setFont(new Font("Lato", Font.PLAIN, 20));
		usernameTxtfield.setBounds(52, 239, 238, 35);
		mainMenuPanel.add(usernameTxtfield);
		usernameTxtfield.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(51, 153, 204));
		lblPassword.setFont(new Font("Roboto Condensed", Font.PLAIN, 30));
		lblPassword.setBounds(52, 285, 141, 41);
		mainMenuPanel.add(lblPassword);
		
		passwordTxtfield = new JPasswordField();
		passwordTxtfield.setBounds(52, 330, 237, 35);
		mainMenuPanel.add(passwordTxtfield);
		passwordTxtfield.setColumns(10);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchCards();
			}
		});
		btnLogin.setFont(new Font("Roboto Condensed", Font.PLAIN, 17));
		btnLogin.setBounds(190, 376, 100, 35);
		mainMenuPanel.add(btnLogin);
		
		lblSignUp = new JLabel("Sign Up");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				switchCards();
			}
		});
		lblSignUp.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		lblSignUp.setBounds(62, 381, 74, 22);
		mainMenuPanel.add(lblSignUp);
		
	}

	
	public void switchCards()
	{
		boolean userEmpty = usernameTxtfield.getText().isEmpty();
		boolean pwEmpty = passwordTxtfield.getPassword().toString().isEmpty();
		if(!userEmpty && !pwEmpty)
		{
			//client.login(outBuffer, inBuffer)
			CardLayout cLayout = (CardLayout) contentPane.getLayout();
			cLayout.show(contentPane, "gameLobbyCard");
		}
		else if(userEmpty)
		{
			JOptionPane.showMessageDialog(null, "Username field cannot be empty");
		}
		else if(pwEmpty)
		{
			JOptionPane.showMessageDialog(null, "Password field cannot be empty");
		}
		else {
			JOptionPane.showMessageDialog(null, "Well this is embarrassing. I don't know what is wrong. Try again please. D:");
		}
	}
}
