package Game;

import java.util.Timer;
import java.util.TimerTask;

public class TimerSpawn {

	private Fase fase;
	private int horda;
	private int darkHoleNumber = 0, bulletsNumber = 0;
	private Timer timerDarkHoles, timerBullets;
	private TimerTask timerTaskDarkHoles = new TimerTask() {
		
		@Override
		public void run() {
			if(!fase.getDarkHoleX().isEmpty() && !fase.getDarkHoleY().isEmpty() && fase.getHorda() == horda) {
				for(int i = 0; i < fase.getHorda(); i++) {
					darkHoleNumber++;
					System.out.println("N�mero de Spawns de Buracos Negros: x" + darkHoleNumber);
					fase.getDarkHoles().add(new DarkHole(fase.getDarkHoleX().get(0), fase.getDarkHoleY().get(0)));
					fase.getDarkHoleX().remove(0);
					fase.getDarkHoleY().remove(0);
				}
			} else {
				timerTaskDarkHoles.cancel();
			}
		}
		
	};
			
	private TimerTask timerTaskBullets = new TimerTask() {

		@Override
		public void run() {
			if(!fase.getBulletsX().isEmpty() && !fase.getBulletsY().isEmpty() && fase.getHorda() == horda) {
				bulletsNumber++;
				System.out.println("N�mero de Spawns de Muni��es: x" + bulletsNumber);
				fase.getBullets().add(new Bullets(fase.getBulletsX().get(0), fase.getBulletsY().get(0)));
				fase.getBulletsX().remove(0);
				fase.getBulletsY().remove(0);
			} else {
				timerTaskBullets.cancel();
			}
		}
		
	};
	
	public TimerSpawn(int whichTimer, int delay, Fase fase, int horda) {
		
		this.fase = fase;
		this.horda = horda;
		
		//0 for DarkHoles / 1 for Bullets
		switch (whichTimer) {
			case 0:
				timerDarkHoles = new Timer();
				timerDarkHoles.schedule(timerTaskDarkHoles, delay, delay);
				break;
			case 1:
				timerBullets = new Timer();
				timerBullets.schedule(timerTaskBullets, delay, delay);
				break;
		}
		
	}

	
}
