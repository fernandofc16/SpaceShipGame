package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Shot {

	private Image picture;
	private int x, y;
	private int largura, altura;
	private boolean isVisible;
	
	private static final int LARGURA_DA_TELA = 1200;
	private static final int VELOCIDADE_SHOT = 8;
	
	public Shot(int x, int y) {
		this.x = x;
		this.y = y;
		
		ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/tiro.png"));
		picture = referencia.getImage();
		largura = picture.getWidth(null);
		altura = picture.getWidth(null);
		
		isVisible = true;
	}
	
	public void move() {
		this.x += VELOCIDADE_SHOT;
		if(x > LARGURA_DA_TELA) {
			isVisible = false;
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
