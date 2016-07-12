package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class DarkHole {

	private Image picture;
	private int x, y;
	private boolean isVisible, isBig;

	public DarkHole(int x, int y) {
		
		this.x = x;
		this.y = y;
		isVisible = true;
		isBig = false;
		
		ImageIcon referencia1 = new ImageIcon(this.getClass().getResource("/Images/darkHoleFirst.png"));
		picture = referencia1.getImage();
		
		new TimerDarkHoleGrown(this);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 60, 60);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return picture;
	}


	public boolean getIsVisible() {
		return isVisible;
	}
	
	public void killSpaceShip() {
		SpaceShip.life = 0;
	}
	
	public void setDarkHoleBig() {
		ImageIcon referencia1 = new ImageIcon(this.getClass().getResource("/Images/darkHole.png"));
		picture = referencia1.getImage();
		isBig = true;
	}
	
	public void setDarkHoleVisible(boolean visible) {
		this.isVisible = visible;
	}
	
	public boolean getIsBig() {
		return isBig;
	}
}
