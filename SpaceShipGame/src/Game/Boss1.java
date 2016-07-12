package Game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Boss1 {
	
	private int x = 850, y = 350;
	private ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/bossalien1.png"));
	private Image picture = referencia.getImage();
	private int largura = picture.getWidth(null), altura = picture.getHeight(null);
	private int bossLife = 50;
	private boolean moveUp = true;
	private Fase fase;
	
	public Boss1(Fase fase) {
		this.fase = fase;
	}

	public int getBossLife() {
		return bossLife;
	}

	public void setBossLife(int bossLife) {
		this.bossLife = bossLife;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}
	
	public Image getPicture() {
		return picture;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void move() {

		if(y >= 0 && moveUp) {
			y -= 1;
		} else {
			moveUp = false;
		}
		
		if(y <= 700 - altura && moveUp == false) {
			y += 1;
		} else {
			moveUp = true;
		}
		
	}
	
	public void shot() {
		fase.getBossShots().add(new BossShot(x, y + 200));	
	}

}
