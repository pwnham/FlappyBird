package src;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import src.FlappyPanel;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		FlappyPanel panel = new FlappyPanel();
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setVisible(true);		
		frame.setSize(1000, 600);
	}

}
