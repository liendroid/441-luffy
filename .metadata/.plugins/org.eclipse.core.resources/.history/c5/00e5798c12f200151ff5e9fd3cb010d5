package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {
	private JTextField userTextField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					GameLobby gFrame = new GameLobby();
					frame.setVisible(true);
					gFrame.setVisible(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		getContentPane().setFont(new Font("Avenir Next Rounded Pro Medium", Font.PLAIN, 14));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 677);
		getContentPane().setLayout(null);
		
		userTextField = new JTextField();
		userTextField.setBounds(54, 174, 172, 29);
		getContentPane().add(userTextField);
		userTextField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Gotham Light", Font.PLAIN, 14));
		lblPassword.setBounds(54, 224, 83, 22);
		getContentPane().add(lblPassword);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Gotham Light", Font.PLAIN, 14));
		lblUsername.setBounds(54, 147, 83, 22);
		getContentPane().add(lblUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(54, 248, 172, 29);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setFont(new Font("Gotham Light", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(163, 289, 89, 23);
		getContentPane().add(btnNewButton);

	}
}
