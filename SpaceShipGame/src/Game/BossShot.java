package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class BossShot {

	private Image picture;
	private int x, y;
	private int largura, altura;
	private boolean isVisible;
	private boolean moveUp, moveRight;
	private static int BOSS_SHOT_SPEED = 2;
	private boolean upAndDown;
		
	public BossShot(int x, int y) {
		this.x = x;
		this.y = y;
		
		if(Math.round(Math.random()) == 1) {
			upAndDown = true;
		} else {
			upAndDown = false;
		}		
		
		ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/bossShot.png"));
		picture = referencia.getImage();
		largura = picture.getWidth(null);
		altura = picture.getWidth(null);
		isVisible = true;
		if(upAndDown) {
			moveUp = true;
		} else {
			moveUp = false;
		}
		moveRight = true;
	}
	
	public void move() {

		if(y >= 0 && moveUp) {
			y -= BOSS_SHOT_SPEED;
		} else {
			moveUp = false;
		}
		
		if(y <= 650 && moveUp == false) {
			y += BOSS_SHOT_SPEED;
		} else {
			moveUp = true;
		}
		
		if(x >= 0 && moveRight) {
			x -= BOSS_SHOT_SPEED;
		} else {
			moveRight = false;
		}
		
		if(x <= 1200 - largura && moveRight == false) {
			x += BOSS_SHOT_SPEED;
		} else {
			moveRight = true;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Image getPicture() {
		return picture;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
