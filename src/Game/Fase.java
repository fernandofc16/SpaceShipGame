package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 122558497202059848L;
	private Image fundo;
	private SpaceShip spaceShip;
	private Timer timer;
	private String causeOfDeath;
	private boolean inGame;
	private boolean youWin;
	private boolean allEnemiesDefeat;
	private boolean lifeBarVisible;
	private boolean informDownVisible;
	private ArrayList<DarkHole> darkHoles;
	private ArrayList<Bullets> bullets;
	private ArrayList<Inimigo> inimigos;
	private ArrayList<Integer> coordenadasX;
	private ArrayList<Integer> coordenadasY;
	private ArrayList<Integer> bulletsX;
	private ArrayList<Integer> bulletsY;
	private ArrayList<Integer> darkHoleX;
	private ArrayList<Integer> darkHoleY;
	private ArrayList<BossShot> bossShots;
	private int score;
	private int horda;
	private int timeInicio;
	private int timeDecorrido;
	private int inimigosMortos;
	private boolean bossTime;
	private Boss1 boss1;
	private Rectangle tryAgainRec = new Rectangle(920 , 560, 250, 50);
	public boolean bossAlive;
	public boolean bossed;
	
	public Fase() {
		
		timeInicio = (int)System.currentTimeMillis();
		horda = 1;
		inimigosMortos = 0;
		score = 0;
		allEnemiesDefeat = false;
		youWin = false;
		causeOfDeath = "";
		inimigos = new ArrayList<>();
		coordenadasX = new ArrayList<>();
		coordenadasY = new ArrayList<>();
		bulletsX = new ArrayList<>();
		bulletsY = new ArrayList<>();
		bullets = new ArrayList<Bullets>();
		darkHoleX = new ArrayList<>();
		darkHoleY = new ArrayList<>();
		darkHoles = new ArrayList<>();
		setEnimiesLocation();
		setBulletsLocation();
		setDarkHoleLocation();
		SpaceShip.numberOfBullets = 50;
		lifeBarVisible = true;
		informDownVisible = true;
		bossTime = false;
		boss1 = new Boss1(this);
		bossAlive = true;
		bossShots = new ArrayList<BossShot>();
		bossed = false;
		
		inGame = true;
		setDoubleBuffered(true);
		setFocusable(true);
		addKeyListener(new TecladoAdapter());
		
		
		//ImageIcon referencia = new ImageIcon("C:\\SpaceShip\\cenario.jpg");
        ImageIcon referencia = new ImageIcon(this.getClass().getResource("/Images/cenario.jpg"));
        fundo = referencia.getImage();

		spaceShip = new SpaceShip();
		
		inicializarInimigos();
		inicializarBullets();
		inicializarDarkHole();
		
		timer = new Timer(5, this);
		timer.start();
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(tryAgainRec.contains(e.getPoint()) && spaceShip.isAlive() == false && inGame == false || youWin && inGame == false) {
					MainJanelas.mainJ.changeJPanel(MainJanelas.beginJanela);
				}
			}
			
		});
		
	}
	
	public SpaceShip getSpaceShip() {
		return spaceShip;
	}
	
	public int getHorda() {
		return horda;
	}

	public void setHorda(int horda) {
		this.horda = horda;
	}

	public void setNumberOfBullets() {
			SpaceShip.numberOfBullets += horda*3;
	}
	
	public void setEnimiesLocation() {
		
		for(int i = 0; i < horda*5; i++) {

			int cooX = ((int)(Math.random()*1201)) + 1200;
			int cooY = ((int)(Math.random()*571));
			
			if(cooY <= 42) {
				cooY += 50;
			}

			coordenadasX.add(cooX);
			coordenadasY.add(cooY);
			
		}
	}
	
	public void setBulletsLocation() {
		
		for(int i = 0; i < 3*horda; i++) {
			int buX = (int)(Math.random()*601);
			int buY = (int)(Math.random()*571);

			bulletsX.add(buX);
			bulletsY.add(buY);

		}
	}
	
	public void setDarkHoleLocation() {
		
		for (int i = 0; i < 10*horda; i++) {
			
			int dkX = (int)(Math.random()*1001);
			int dkY = (int)(Math.random()*571);

			darkHoleX.add(dkX);
			darkHoleY.add(dkY);
		}
	}

	
	public void inicializarInimigos() {
		for(int i = 0; i < coordenadasX.size(); i++) {
			inimigos.add(new Inimigo(coordenadasX.get(i), coordenadasY.get(i)));
		}
	}
	
	public void inicializarBullets() {
		new TimerSpawn(1, 7000, this, horda);
	}
	
	public void inicializarDarkHole() {
		new TimerSpawn(0, 10000, this, horda);
	}
	
	public ArrayList<Integer> getBulletsX() {
		return bulletsX;
	}

	public void setBulletsX(ArrayList<Integer> bulletsX) {
		this.bulletsX = bulletsX;
	}

	public ArrayList<Integer> getBulletsY() {
		return bulletsY;
	}

	public void setBulletsY(ArrayList<Integer> bulletsY) {
		this.bulletsY = bulletsY;
	}

	public ArrayList<Integer> getDarkHoleX() {
		return darkHoleX;
	}

	public void setDarkHoleX(ArrayList<Integer> darkHoleX) {
		this.darkHoleX = darkHoleX;
	}

	public ArrayList<Integer> getDarkHoleY() {
		return darkHoleY;
	}

	public void setDarkHoleY(ArrayList<Integer> darkHoleY) {
		this.darkHoleY = darkHoleY;
	}

	public void setDarkHoles(ArrayList<DarkHole> darkHoles) {
		this.darkHoles = darkHoles;
	}

	public void setBullets(ArrayList<Bullets> bullets) {
		this.bullets = bullets;
	}

	public void verificarTripleShot() {
		if(SpaceShip.tripleShotCharge == 5) {
			SpaceShip.tripleShot = true;
		}
	}
	
	
	
	public void bossShoting() {
		if(timeDecorrido % 200 == 0) {
			boss1.shot();
		}
	}
	
	public void verificarBossTime() {
		if(horda == 10 && bossed == false && inimigos.size() == 0) {
			bossAlive = true;
			bossTime = true;
			bossed = true;
		}
	}
		
	public void paint(Graphics g) {

		timeDecorrido = ((int)System.currentTimeMillis() - timeInicio);
		verificarTripleShot();
		//verificarInimigos();
		verificarBossTime();
		
		Graphics2D grafico = (Graphics2D) g;
		grafico.drawImage(fundo, 0, 0, null);
		
	if(inGame && allEnemiesDefeat == false) {
		
		for(int i = 0; i < darkHoles.size(); i++) {
			if(darkHoles.get(i).getIsVisible()) {
				grafico.drawImage(darkHoles.get(i).getImage(), darkHoles.get(i).getX(), darkHoles.get(i).getY(), this);						
			} else {
				darkHoles.remove(i);
			}
		}
		
		grafico.drawImage(spaceShip.getPicture(), spaceShip.getX(), spaceShip.getY(), this);
		
		List<Shot> shots = spaceShip.getShots();
		List<Shot> shots2 = spaceShip.getShots2();
		List<Shot> shots3 = spaceShip.getShots3();
		
		for (Shot shot : shots) {
			Shot s = (Shot) shot;
			grafico.drawImage(s.getPicture(), s.getX(), s.getY(), this); // arg0 - (Shot) shot - cast/ conversão para a classe do objeto
		}

		for (Shot shot : shots2) {
			Shot s = (Shot) shot;
			grafico.drawImage(s.getPicture(), s.getX(), s.getY(), this); // arg0 - (Shot) shot - cast/ conversão para a classe do objeto
		}

		for (Shot shot : shots3) {
			Shot s = (Shot) shot;
			grafico.drawImage(s.getPicture(), s.getX(), s.getY(), this); // arg0 - (Shot) shot - cast/ conversão para a classe do objeto
		}
		
		if(bossTime) {
			bossShoting();
			grafico.drawImage(boss1.getPicture(), boss1.getX(), boss1.getY(), this);
			for (BossShot bossShots : bossShots) {
				BossShot bs = (BossShot) bossShots;
				grafico.drawImage(bs.getPicture(), bs.getX(), bs.getY(), this);
			}
		} else {
			for (Inimigo in : inimigos) {
				grafico.drawImage(in.getPicture(), in.getX(), in.getY(), this);
			}
		}
		
		for (Bullets bls : bullets) {
			Bullets bl = (Bullets) bls;
			grafico.drawImage(bl.getPicture(), bl.getX(), bl.getY(), this);
		}
		
		if(lifeBarVisible) {
			if(SpaceShip.tripleShotCharge == 0) {
				grafico.drawImage(spaceShip.getBarra0(), 48, 18, this);
			} else if(SpaceShip.tripleShotCharge == 1) {
				grafico.drawImage(spaceShip.getBarra1(), 48, 18, this);	
			} else if(SpaceShip.tripleShotCharge == 2) {
				grafico.drawImage(spaceShip.getBarra2(), 48, 18, this);	
			} else if(SpaceShip.tripleShotCharge == 3) {
				grafico.drawImage(spaceShip.getBarra3(), 48, 18, this);	
			} else if(SpaceShip.tripleShotCharge == 4) {
				grafico.drawImage(spaceShip.getBarra4(), 48, 18, this);	
			} else if(SpaceShip.tripleShotCharge == 5) {
				grafico.drawImage(spaceShip.getBarra5(), 48, 18, this);	
			}
		
			if(SpaceShip.life == 1) {
				grafico.drawImage(spaceShip.getLifeBar1(), 5, 10, this);
			} else if (SpaceShip.life == 2) {
				grafico.drawImage(spaceShip.getLifeBar2(), 5, 10, this);
			} else {
				grafico.drawImage(spaceShip.getLifeBar3(), 5, 10, this);
			}
		}
		
		Font font = new Font("Verdana", Font.BOLD, 20);
		grafico.setColor(Color.WHITE);
		grafico.setFont(font);
		if(bossTime) {
			grafico.drawString("Boss Life: " + boss1.getBossLife(), 920, 20);
		} else {
			grafico.drawString("Inimigos Restantes: " + inimigos.size(), 920, 20);
		}
		if(informDownVisible) {
			grafico.drawString("Horda: " + horda, 5, 665);
			grafico.drawString("Munição: " + SpaceShip.numberOfBullets, 5, 640);
		}
		grafico.drawString("Score: " + score, 1020, 665);

	} else if(spaceShip.isAlive() == false && inGame == false) {
		ImageIcon gameOver = new ImageIcon(this.getClass().getResource("/Images/gameOver.png"));
		grafico.drawImage(gameOver.getImage(), 0, 0, null);
		Font font = new Font("Verdana", Font.BOLD, 30);
		grafico.setColor(Color.BLACK);
		grafico.setFont(font);
		grafico.drawString("Seu score: " + score, 450, 640);
		grafico.drawString("Horda: " + horda, 450, 600);
		grafico.drawString("Inimigos mortos: " + inimigosMortos, 450, 560);
		grafico.setFont(new Font("Verdana", Font.PLAIN, 25));
		grafico.drawString(causeOfDeath, 150, 110);
		grafico.setFont(new Font("Arial", Font.BOLD, 45));
		grafico.setColor(Color.WHITE);
		grafico.drawString("TRY AGAIN", 920 , 600);
		
	} else if(youWin && inGame == false) {
		ImageIcon youWin = new ImageIcon(this.getClass().getResource("/Images/youWin.png"));
		grafico.drawImage(youWin.getImage(), 0, 0, null);
		Font font = new Font("Verdana", Font.BOLD, 30);
		grafico.setColor(Color.BLACK);
		grafico.setFont(font);
		grafico.drawString("Seu score: " + score, 5, 640);
		grafico.drawString("Horda: " + horda, 5, 600);
		grafico.drawString("Inimigos mortos: " + inimigosMortos, 5, 560);
		grafico.drawString("PLAY AGAIN", 920 , 600);
	} else if(inGame && allEnemiesDefeat) {
		nextHorda();
		allEnemiesDefeat = false;
	}
	
	g.dispose(); // limpar tela para proxima pintura
}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}
	
	public void nextHorda() {
		horda++;
		coordenadasX.clear();
		coordenadasY.clear();
		setEnimiesLocation();
		setBulletsLocation();
		setDarkHoleLocation();
		inicializarInimigos();
		inicializarBullets();
		inicializarDarkHole();
		setNumberOfBullets();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(inimigos.size() == 0 && bossAlive == false && horda == 10) {
			inGame = false;
			youWin = true;
		} else if(inimigos.size() == 0 && horda < 10) {
			nextHorda();
		}
		
		List<Shot> shots = spaceShip.getShots();
		List<Shot> shots2 = spaceShip.getShots2();
		List<Shot> shots3 = spaceShip.getShots3();
		
		for (int i = 0; i<shots.size(); i++) {
			Shot s = (Shot) shots.get(i);
			if (s.isVisible()) {
				s.move();
			} else {
				shots.remove(s);
			}
		}
		
		for (int i = 0; i<shots2.size(); i++) {
			Shot s = (Shot) shots2.get(i);
			if (s.isVisible()) {
				s.move();
			} else {
				shots2.remove(s);
			}
		}
		
		for (int i = 0; i<shots3.size(); i++) {
			Shot s = (Shot) shots3.get(i);
			if (s.isVisible()) {
				s.move();
			} else {
				shots3.remove(s);
			}
		}

		for (int i = 0; i<inimigos.size(); i++) {
			Inimigo in = inimigos.get(i);
			if (in.isVisible() && bossTime == false) {
				in.move();
			} else {
				inimigos.remove(in);
			}
		}
		
		for (int i=0; i<bullets.size(); i++) {
			Bullets bu = bullets.get(i);
			if (bu.itWasTaken()) {
				bullets.remove(bu);
			}
		}

		for (int i=0; i<bossShots.size(); i++) {
			BossShot bs = bossShots.get(i);
			if(bs.isVisible()) {
				bs.move();
			} else {
				bossShots.remove(bs);
			}
		}
		
		if (bossAlive) {
			boss1.move();
		}

		spaceShip.move();
		if(spaceShip.isAlive()) {
			checkColision();
		}
		repaint();
	}
	
	public void checkColision() {
		
		Rectangle formaNave = spaceShip.getBounds();
		Rectangle formaBoss1 = boss1.getBounds();
		Rectangle formaLifeBar = spaceShip.getBoundsLifeBar();
		Rectangle formaInformDown = new Rectangle(10, 600, 130, 40);
		Rectangle formaBoss1Shots;
		Rectangle formaDarkHole;
		Rectangle formaInimigo;
		Rectangle formaShot;
		Rectangle formaShot2;
		Rectangle formaShot3;
		Rectangle formaBullets;

		
		//Intersection of spaceShip  and enemies
		for(int i = 0; i < inimigos.size(); i++) {
			Inimigo tempInimigo = inimigos.get(i);
			formaInimigo = tempInimigo.getBounds();
			
			if(formaNave.intersects(formaInimigo) && !tempInimigo.getIsDead()) {
				SpaceShip.life -= 1;
			    tempInimigo.setPicture(new ImageIcon(this.getClass().getResource("/Images/big_explosion.gif")).getImage());
				tempInimigo.setSpeed(0);
				tempInimigo.setIsDead(true);
				tempInimigo.setVisibleFalse();
			}
			
			if(bossTime && formaNave.intersects(formaBoss1)) {
				spaceShip.setAlive(false);
				inGame = false;
				causeOfDeath = "Você trombou direto com o Chefe, sua nave não aguentou o tranco!";
			}
			
			if(SpaceShip.life == 0 && !bossTime) {
				spaceShip.setAlive(false);
				inGame = false;
				causeOfDeath = "Você trombou com muitos aliens, sua nave não suportou tantos impactos!";
			}
		}
		
		//Intersection of spaceShip shots and Boss1 / Enemies
		for (Shot tempShots : spaceShip.getShots()) {
		formaShot = tempShots.getBounds();
			if(bossTime) {				
				if(formaShot.intersects(formaBoss1)) {
					tempShots.setVisible(false);
					boss1.setBossLife(boss1.getBossLife() - 1);
				}
				if(boss1.getBossLife() <= 0) {
					bossTime = false;
					bossAlive = false;
				}
			} else {			
				for (Inimigo tempInimigo : inimigos) {
					formaInimigo = tempInimigo.getBounds();
				
					if(formaShot.intersects(formaInimigo)) {
						if(tempInimigo.isSuper() && tempInimigo.lifeSuper > 0) {
							tempShots.setVisible(false);
							tempInimigo.lifeSuper -= 1;
						} else if(tempInimigo.isSuper() && tempInimigo.lifeSuper <= 0) {	
							if(!tempInimigo.getIsDead()) {
								tempInimigo.setPicture(new ImageIcon(this.getClass().getResource("/Images/big_explosion.gif")).getImage());
								tempInimigo.setSpeed(0);
								tempInimigo.setIsDead(true);
								tempInimigo.setVisibleFalse();
								tempShots.setVisible(false);
								score += 300;
								inimigosMortos += 1;
							}
						} else {
							if(!tempInimigo.getIsDead()) {
								tempInimigo.setPicture(new ImageIcon(this.getClass().getResource("/Images/explosion.gif")).getImage());
								tempInimigo.setSpeed(0);
								tempInimigo.setIsDead(true);
								tempInimigo.setVisibleFalse();
								tempShots.setVisible(false);
								score += 100;
								inimigosMortos += 1;
							}
						}
					}
				}
			}
		}
		
		//Intersection of spaceShot2 and Boss1 / Enemies
		for (Shot tempShots : spaceShip.getShots2()) {
			formaShot2 = tempShots.getBounds();
			
			if(bossTime) {
				if(formaShot2.intersects(formaBoss1)) {
					tempShots.setVisible(false);
					boss1.setBossLife(boss1.getBossLife() - 1);
				}
				if(boss1.getBossLife() <= 0) {
					bossTime = false;
					bossAlive = false;
				}
			} else {

				for (Inimigo tempInimigo : inimigos) {
					formaInimigo = tempInimigo.getBounds();
				
					if(formaShot2.intersects(formaInimigo)) {
						if(tempInimigo.isSuper() == true && tempInimigo.lifeSuper > 0) {
							tempShots.setVisible(false);
							tempInimigo.lifeSuper -= 1;
						} else {
							if(!tempInimigo.getIsDead()) {
								tempInimigo.setPicture(new ImageIcon(this.getClass().getResource("/Images/explosion.gif")).getImage());
								tempInimigo.setSpeed(0);
								tempInimigo.setIsDead(true);
								tempInimigo.setVisibleFalse();
								tempShots.setVisible(false);
								score += 100;
								inimigosMortos += 1;
							}
						}
					}
				}
			}
		}
		
		//Intersection of spaceShot3 and Boss1 / Enemies
		for (Shot tempShots : spaceShip.getShots3()) {
			formaShot3 = tempShots.getBounds();
			
			if(bossTime) {
				if(formaShot3.intersects(formaBoss1)) {
					tempShots.setVisible(false);
					boss1.setBossLife(boss1.getBossLife() - 1);
				}
				if(boss1.getBossLife() <= 0) {
					bossTime = false;
					bossAlive = false;
				}
			} else {
	
				for (Inimigo tempInimigo : inimigos) {
					formaInimigo = tempInimigo.getBounds();
				
					if(formaShot3.intersects(formaInimigo)) {
						if(tempInimigo.isSuper() == true && tempInimigo.lifeSuper > 1) {
							tempShots.setVisible(false);
							tempInimigo.lifeSuper -= 1;
						} else {
							if(!tempInimigo.getIsDead()) {
								tempInimigo.setPicture(new ImageIcon(this.getClass().getResource("/Images/explosion.gif")).getImage());
								tempInimigo.setSpeed(0);
								tempInimigo.setIsDead(true);
								tempInimigo.setVisibleFalse();
								tempShots.setVisible(false);
								score += 100;
								inimigosMortos += 1;
							}
						}				
					}
				}
			}
		}
		
		//Intersection of spaceShip and bullets on space
		for (Bullets bu : bullets) {
			formaBullets = bu.getBounds();
			
			if(formaNave.intersects(formaBullets)) {
				bu.reload();
				bu.setitWasTaken(true);
			}
		}
		
		//Intersection of spaceShip and lifeBar
		if(formaLifeBar.intersects(formaNave)) {
			lifeBarVisible = false;
		} else {
			lifeBarVisible = true;
		}
		
		//Intersection of spaceShip and Informations at bottom
		if(formaInformDown.intersects(formaNave)) {
			informDownVisible = false;
		} else {
			informDownVisible = true;
		}
		
		//Intersection of bossShots and spaceShip
		for (BossShot bossShots : bossShots) {
			formaBoss1Shots = bossShots.getBounds();
			if(formaBoss1Shots.intersects(formaNave)) {
				bossShots.setVisible(false);
				SpaceShip.life -= 1;
			}
				
			if(SpaceShip.life == 0) {
				spaceShip.setAlive(false);
				inGame = false;
				causeOfDeath = "Você foi destruido pelo chefe";
			}
		}
	
		//Intersection of darkHoles with enemies and spaceShip
		for(int i = 0; i < darkHoles.size(); i++) {	
			formaDarkHole = darkHoles.get(i).getBounds();

				if(formaDarkHole.intersects(formaNave) && darkHoles.get(i).getIsBig()) {
					darkHoles.get(i).killSpaceShip();
					causeOfDeath = "Você foi sugado para um buraco negro!";
				}
				
				for (Inimigo tempInimigo : inimigos) {
					formaInimigo = tempInimigo.getBounds();
					if(formaDarkHole.intersects(formaInimigo) && darkHoles.get(i).getIsBig()) {
						tempInimigo.setIsSuper(true);
					}
				}
		}
	}
	
	
	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			spaceShip.keyPressed(arg0);
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			spaceShip.keyReleased(arg0);
		}
	}


	public ArrayList<DarkHole> getDarkHoles() {
		return darkHoles;
	}

	public ArrayList<Bullets> getBullets() {
		return bullets;
	}

	public ArrayList<Inimigo> getInimigos() {
		return inimigos;
	}
	
	public ArrayList<BossShot> getBossShots() {
		return bossShots;
	}
}
