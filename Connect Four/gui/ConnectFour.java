package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseEvent;
import javax.swing.JList;

public class ConnectFour extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTxtfield;
	private JPasswordField passwordTxtfield;
	private JLabel lblSignUp;

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
		
		JPanel mainMenuPanel = new JPanel();
		contentPane.add(mainMenuPanel, "mainMenuCard");
		mainMenuPanel.setLayout(null);
		
		JPanel gameLobbyPanel = new JPanel();
		contentPane.add(gameLobbyPanel, "gameLobbyCard");
		gameLobbyPanel.setLayout(null);
		
		JPanel gamesListPanel = new JPanel();
		gamesListPanel.setBounds(46, 73, 705, 375);
		gameLobbyPanel.add(gamesListPanel);
		
		JLabel lblGames = new JLabel("Games");
		lblGames.setForeground(new Color(0, 102, 204));
		lblGames.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 40));
		lblGames.setBounds(46, 36, 102, 33);
		gameLobbyPanel.add(lblGames);
		
		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Roboto Condensed", Font.PLAIN, 20));
		btnNewButton.setBounds(536, 459, 102, 33);
		gameLobbyPanel.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(761, 73, 199, 375);
		gameLobbyPanel.add(list);
		
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
		CardLayout cLayout = (CardLayout) contentPane.getLayout();
		cLayout.show(contentPane, "gameLobbyCard");
	}
}