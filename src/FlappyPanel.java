package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyPanel extends JPanel implements ActionListener, KeyListener{

	private boolean jumping = false;
	private int birdJump = 0;
	private int jumpHeight = 25;
	
	private int birdX = 250; 
	private int birdY = 250;
	private int birdDeltaX = 0;
	private int birdDeltaY = 4;
	private int birdDiameter = 30;
	

	public FlappyPanel() {
		setBackground(Color.CYAN);
		
		setFocusable(true);
		addKeyListener(this);
		
		Timer timer = new Timer(1000/100, this);
		timer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		step();
	}
	
	private void step() {
		
		int birdTop = birdY;
		int birdBottom = birdY + birdDiameter;
		
		int nextBirdTop = birdTop - birdJump;
		
		if (jumping) {
			birdY -= birdJump;
			birdJump -= 2;
			if (nextBirdTop <= 0)
				birdJump = 0;
			if (birdJump <= 0)
				jumping = false;
		}else if (birdBottom < 530) {
			birdY += birdDeltaY;
		}
		repaint();
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.green);
		g.fillRect(0, 520, getWidth(), 10);
		
		g.setColor(new Color(178, 141, 30));
		g.fillRect(0, 530, getWidth(), 70);

		g.setColor(Color.YELLOW);
		g.fillOval(birdX, birdY, birdDiameter, birdDiameter);
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			jumping = true;
			birdJump = jumpHeight;
			System.out.println(birdJump);
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
