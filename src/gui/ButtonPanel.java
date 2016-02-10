package gui;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	private JButton playButton;
	private JButton optionButton;
	private JButton accountButton;
	
	public ButtonPanel(){
		playButton = new JButton("PLAY");
		optionButton = new JButton("OPTION");
		accountButton = new JButton("ACCOUNT");
		
		add(playButton);
		add(optionButton);
		add(accountButton);
		
		playButton.addActionListener(null);
		optionButton.addActionListener(null);
		accountButton.addActionListener(null);
	}

}
