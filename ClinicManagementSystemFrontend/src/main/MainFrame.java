package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

public class MainFrame extends JFrame {

	public MainFrame() {
		super("Logowanie");

		JPanel loginPanel = new LoginPanel();
		add(loginPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setSize(1024,600);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	    
	}
	public static void main(String[] args) {
		new MainFrame();
	}
}
