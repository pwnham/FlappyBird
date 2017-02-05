package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FlappyPanel extends JPanel implements ActionListener, KeyListener {

	private boolean titleScreen = true;
	private boolean playing = false;
	private boolean gameOver = false;

	private int playerScore = 0;

	private boolean jumping = false;
	private int birdJump = 0;
	private int jumpHeight = 13;

	private int birdX = 250;
	private int birdY = 250;
	private int birdDeltaX = 0;
	private int birdDeltaY = 4;
	private int birdDiameter = 30;

	private int boxX = 250;
	private int boxY = 0;
	private int boxWidth = 300;
	private int boxHeight = 150;

	private int pipeSpeed = 1;

	Random ran = new Random();

	Pipe[] pipes = new Pipe[30];

	public FlappyPanel() {
		setBackground(Color.CYAN);

		setFocusable(true);
		addKeyListener(this);

		Timer timer = new Timer(1000 / 100, this);
		timer.start();
		generatePipes();
	}

	public void actionPerformed(ActionEvent e) {
		step();
	}

	private void step() {
		if (playing) {
			int birdTop = birdY;
			int birdBottom = birdY + birdDiameter;

			int nextBirdTop = birdTop - birdJump;

			for (int i = 0; i < pipes.length; i++) {
				pipes[i].setX(pipes[i].getX() - pipeSpeed);
				if (pipes[i].getX() == birdX) {
					playerScore++;
				}
			}

			if (collisionDetected(birdX, birdY, birdDiameter, birdDiameter)) {
				System.out.println("collision detected");
				playing = false;
				gameOver = true;
				gameOver = true;
			}
			if (jumping) {
				birdY -= birdJump;
				birdJump -= 1;
				if (nextBirdTop <= 0)
					birdJump = 0;
				if (birdJump <= 0)
					jumping = false;
			} else if (birdBottom < 530) {
				birdY += birdDeltaY;
			}
			repaint();
		}

	}

	public void generatePipes() {
		for (int i = 0; i < pipes.length; i++) {
			pipes[i] = new Pipe(i * 300 + 800, 0, ran.nextInt(300) + 25);
		}
	}

	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		g.setColor(Color.green);
		g.fillRect(0, 520, getWidth(), 10);

		g.setColor(new Color(178, 141, 30));
		g.fillRect(0, 530, getWidth(), 70);

		if (titleScreen) {
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 100));
			g.setColor(new Color(15, 145, 72));
			g.drawString("Flappy Mohammad", 50, 225);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 48));
			g.drawString("Press space to start", 250, 375);
		} else if (!titleScreen) {

			g.setColor(new Color(15, 145, 72));
			for (int i = 0; i < pipes.length; i++) {
				g.fillRect(pipes[i].getX(), pipes[i].getY(), pipes[i].getWidth(), pipes[i].getH1());
				g.fillRect(pipes[i].getX(), pipes[i].getH2(), pipes[i].getWidth(), 520 - pipes[i].getH2());
			}

			g.setColor(Color.cyan);
			g.drawRect(birdX, birdY, birdDiameter, birdDiameter);
			g.setColor(Color.YELLOW);
			g.fillOval(birdX, birdY, birdDiameter, birdDiameter);

			g.setColor(Color.WHITE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 72));
			g.drawString(String.valueOf(playerScore), 900, 60);

			if (gameOver) {
				g.setColor(Color.white);
				g.fillRect(300, 175, 400, 200);

				g.setColor(Color.BLACK);
				g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
				g.drawString("GAME OVER", 375, 225);
				g.drawString("Score: " + String.valueOf(playerScore), 420, 275);
				g.drawString("Press space to restart", 315, 335);
			}
		}
	}

	public boolean collisionDetected(int x, int y, int width, int height) {
		for (int i = 0; i < pipes.length; i++) {
			if (x + width > pipes[i].getX() && x < pipes[i].getX() + pipes[i].getWidth()) {
				if (y + height > pipes[i].getY() && y < pipes[i].getY() + pipes[i].getH1()) {
					return true;
				} else if (y + height > pipes[i].getH2() && y < pipes[i].getH2() + (520 - pipes[i].getH2())) {
					return true;
				}
			}
		}
		if (y + height >= 530) {
			return true;
		}
		return false;
	}

	public void keyPressed(KeyEvent e) {
		if (titleScreen) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				titleScreen = false;
				playing = true;
			}
		} else if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				jumping = true;
				birdJump = jumpHeight;
				System.out.println(birdJump);
			}
		} else if (gameOver) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				gameOver = false;
				playing = true;
				playerScore = 0;
				birdX = 250;
				birdY = 250;
				generatePipes();
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
