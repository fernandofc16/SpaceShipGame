package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullets {
	
	private int x, y;
	private Image picture;
	private boolean itWasTaken;
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 30, 30);
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

	public boolean itWasTaken() {
		return itWasTaken;
	}

	public void setitWasTaken(boolean itWasTaken) {
		this.itWasTaken = itWasTaken;
	}

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	public Bullets(int x, int y) {
		this.x = x;
		this.y = y;
		ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/bullets.png"));
		picture = referencia.getImage();
		itWasTaken = false;
	}
	
	public void reload() {
		SpaceShip.numberOfBullets += 5;
	}
}
