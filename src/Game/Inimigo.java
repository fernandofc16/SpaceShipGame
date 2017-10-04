package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Inimigo {

	private Image picture;
	private int x, y;
	private int largura, altura;
	private boolean isVisible;
	private boolean isSuper;
	private boolean isDead;
	private static final int LARGURA_DA_TELA = 1200;
	private int speed;
	public int lifeSuper;
	public ImageIcon referencia;
	public int enimieType;
	private Timer timer;
	private TimerTask task = new TimerTask(){
		@Override
		public void run() {
				isVisible = false;
				timer.cancel();
		}
	};
	
	private static int contador = 0;
	
	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;
		speed = 1;
		isSuper = false;
		lifeSuper = 2;
		timer = new Timer();
		
		if (contador++ % 2 == 0) {
			referencia = new ImageIcon(this.getClass().getResource("/Images/inimigo1.1.png"));
			enimieType = 0;
		} else {
			referencia = new ImageIcon(this.getClass().getResource("/Images/inimigo2.1.png"));
			enimieType = 1;
		}
		
		picture = referencia.getImage();
		largura = picture.getWidth(null);
		altura = picture.getHeight(null);
		
		isDead = false;
		isVisible = true;
	}

	public void move() {
		if(x < 0) {
			x = LARGURA_DA_TELA;
		} else {
			x -= speed;
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	public void setIsDead(boolean isDead) {
		this.isDead = isDead;
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getLifeSuper() {
		return lifeSuper;
	}

	public void setLifeSuper(int lifeSuper) {
		this.lifeSuper = lifeSuper;
	}

	public boolean isVisible() {
		return isVisible;
	}
	
	public void setIsSuper(boolean isSuper) {
		this.isSuper = isSuper;
			if(enimieType == 0) {
				this.referencia = new ImageIcon(this.getClass().getResource("/Images/inimigo1.1.super.png"));
				this.setPicture(referencia.getImage());
				this.setLargura(100);
				this.setAltura(100);
			} else {
				this.referencia = new ImageIcon(this.getClass().getResource("/Images/inimigo2.1.super.png"));
				this.setPicture(referencia.getImage());
				this.setLargura(100);
				this.setAltura(100);
			}
	}

	public void setVisibleFalse() {
		timer.schedule(task, 130, 130);
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

	public boolean isSuper() {
		return isSuper;
	}
	
	public void setPicture(Image picture) {
		this.picture = picture;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
