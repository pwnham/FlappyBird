package src;

public class Pipe {

	private int width;
	private int y;
	private int x = 5;
	private int h1;
	private int h2;

	public Pipe(int x, int y, int h1) {
		this.x = x;
		this.y = y;
		this.width = 50;
		this.h1 = h1;
		this.h2 = h1 + 200;
	}
	
	public void setX(int newX) {
		x = newX;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int newY) {
		y = newY;
	}
	
	public int getY() {
		return y;
	}
	
	public void setWidth(int newWidth) {
		width = newWidth;
	}
	
	public int getWidth() {
		return width;
	}

	public void setH1(int newHeight) {
		h1 = newHeight;
	}
	
	public int getH1() {
		return h1;
	}
	
	public void setH2(int newHeight2) {
		h2 = newHeight2;
	}
	
	public int getH2() {
		return h2;
	}
}
