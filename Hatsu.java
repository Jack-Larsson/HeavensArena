import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Hatsu {
	
	private int x, y, w, h, dx;
	private ImageIcon attack;
	
	public Hatsu() {
		x=0;
		y=0;
		w=0;
		h=0;
		dx=0;
		attack = new ImageIcon("");
	}
	public Hatsu(int posx, int posy, int width, int height, int zoom, ImageIcon ability) {
		x=posx;
		y=posy;
		w=width;
		h=height;
		dx=zoom;
		attack= ability;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getAttack() {
		return attack;
	}
	public void setAttack(ImageIcon attack) {
		this.attack = attack;
	}
	public void drawHatsu(Graphics g2d) {
		g2d.drawImage(attack.getImage(), x, y, w, h, null);
	}
	public void move() {
		x+=dx;
	}

}
