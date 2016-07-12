package Game;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class SpaceShip {
	
	private int x, y;
	private int largura, altura;
	private int speedx, speedy;
	private boolean isAlive;
	private Image picture;
	private Image imageBarra0;
	private Image imageBarra1;
	private Image imageBarra2;
	private Image imageBarra3;
	private Image imageBarra4;
	private Image imageBarra5;
	private Image lifebar1;
	private Image lifebar2;
	private Image lifebar3;
    private List<Shot> shots;
    private List<Shot> shots2;
    private List<Shot> shots3;
    
    public List<Shot> getShots2() {
		return shots2;
	}

	public List<Shot> getShots3() {
		return shots3;
	}

	public static int numberOfBullets;
    public static boolean tripleShot;
    public static int tripleShotCharge;
	public static int life;
    
	public SpaceShip() {

		ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/naveStay.png"));
		picture = referencia.getImage();

		ImageIcon referencia0 = new ImageIcon(this.getClass().getResource("/Images/tpshot0.png"));
		imageBarra0 = referencia0.getImage();

		ImageIcon referencia1 = new ImageIcon(this.getClass().getResource("/Images/tpshot1.png"));
		imageBarra1 = referencia1.getImage();

		ImageIcon referencia2 = new ImageIcon(this.getClass().getResource("/Images/tpshot2.png"));
		imageBarra2 = referencia2.getImage();

		ImageIcon referencia3 = new ImageIcon(this.getClass().getResource("/Images/tpshot3.png"));
		imageBarra3 = referencia3.getImage();

		ImageIcon referencia4 = new ImageIcon(this.getClass().getResource("/Images/tpshot4.png"));
		imageBarra4 = referencia4.getImage();

		ImageIcon referencia5 = new ImageIcon(this.getClass().getResource("/Images/tpshot5.png"));
		imageBarra5 = referencia5.getImage();

		ImageIcon referencialife1 = new ImageIcon(this.getClass().getResource("/Images/lifeship1.png"));
		lifebar1 = referencialife1.getImage();

		ImageIcon referencialife2 = new ImageIcon(this.getClass().getResource("/Images/lifeship2.png"));
		lifebar2 = referencialife2.getImage();

		ImageIcon referencialife3 = new ImageIcon(this.getClass().getResource("/Images/lifeship3.png"));
		lifebar3 = referencialife3.getImage();

		life = 3;
		largura = picture.getWidth(null);
		altura = picture.getHeight(null);
		isAlive = true;
		tripleShot = false;
		shots = new ArrayList<Shot>();
		shots2 = new ArrayList<Shot>();
		shots3 = new ArrayList<Shot>();
		this.x = 200;
		this.y = 200;
		tripleShotCharge = 0;
		
	}

	public void move() {
		x += speedx;
		y += speedy;
		
		if(speedy < 0) {
			ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/naveFrontUp1.png"));
			picture = referencia.getImage();
		} else if(speedy > 0) {
			ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/naveFrontDown1.png"));
			picture = referencia.getImage();			
		} else if(speedx > 0 && speedy == 0) {
			ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/naveFront1.png"));
			picture = referencia.getImage();
		} else {
			ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/naveStay.png"));
			picture = referencia.getImage();
		}
		
		if (this.x < 4) {
			x = 2;
		}
		if (this.y < -8) {
			y = -8;
		}
		if (this.x > 1092) {
			x = 1092;
		}
		if (this.y > 620) {
			y = 620;
		}
	}
	

	
	public void shot() {
		if(tripleShot) {
			if(numberOfBullets > 0 && tripleShotCharge == 5) {
				this.shots.add(new Shot(x + 110, y + 35));
				this.shots2.add(new Shot(x + 100, y + 65));
				this.shots3.add(new Shot(x + 100, y + 5));
				numberOfBullets --;
				tripleShot = false;
				tripleShotCharge = 0;
			}
		} else {
			if(numberOfBullets > 0 && tripleShotCharge < 5) {
				this.shots.add(new Shot(x + 100, y + 35));
				numberOfBullets --;
				tripleShotCharge += 1;
			}
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
	
	public Rectangle getBoundsLifeBar() {
		return new Rectangle(5, 10, 210, 60);
	}
	
	public List<Shot> getShots() {
		return shots;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getPicture() {
		return picture;
	}
	
	public Image getBarra0() {
		return imageBarra0;
	}
	
	public Image getBarra1() {
		return imageBarra1;
	}

	public Image getBarra2() {
		return imageBarra2;
	}

	public Image getBarra3() {
		return imageBarra3;
	}

	public Image getBarra4() {
		return imageBarra4;
	}

	public Image getBarra5() {
		return imageBarra5;
	}

	public Image getLifeBar1() {
		return lifebar1;
	}

	public Image getLifeBar2() {
		return lifebar2;
	}

	public Image getLifeBar3() {
		return lifebar3;
	}

	
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public int getNumberOfBullets() {
		return numberOfBullets;
	}

	public void setNumberOfBullets(int numberOfBullets) {
		SpaceShip.numberOfBullets = numberOfBullets;
	}

	public void keyPressed(KeyEvent tecla) {
		int code = tecla.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			speedy = -2;
		} else if(code == KeyEvent.VK_DOWN) {
			speedy = 2;
		} else if(code == KeyEvent.VK_LEFT) {
			speedx = -2;
		} else if(code == KeyEvent.VK_RIGHT) {
			speedx = 2;
		}
	
	}
	
	public void keyReleased(KeyEvent tecla) {
		int code = tecla.getKeyCode();
		if (code == KeyEvent.VK_UP) {
			speedy = 0;
		} else if(code == KeyEvent.VK_DOWN) {
			speedy = 0;
		} else if(code == KeyEvent.VK_LEFT) {
			speedx = 0;
		} else if(code == KeyEvent.VK_RIGHT) {
			speedx = 0;
		}
		if (code == KeyEvent.VK_SPACE) {
			shot();
		}
	}
}
