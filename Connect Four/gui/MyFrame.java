package gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {
	
	public MyFrame(){
		setTitle("BattleShip 441");
		setExtendedState(Frame.MAXIMIZED_BOTH); 		//Ensure the frame is the size of the screen
		//setUndecorated(true);    						This will make it truly full screen
		
		// Terminates the program
		addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent e) {
			   System.exit(0);
		  	} 
		} );
		
		Container contentPane = getContentPane();
		contentPane.add(new ButtonPanel());
	}
}
