package Game;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDarkHoleGrown {

	private DarkHole darkHole;
	private Timer timerDarkHoleGrown, timerRemoveDarkHole;
	private TimerTask timerTaskRemoveDarkHole = new TimerTask() {

		@Override
		public void run() {
			darkHole.setDarkHoleVisible(false);
			timerRemoveDarkHole.cancel();
		}

		}, timerTaskDarkHoleGrown = new TimerTask() {
		
		@Override
		public void run() {
			darkHole.setDarkHoleBig();
			timerRemoveDarkHole = new Timer();
			timerRemoveDarkHole.schedule(timerTaskRemoveDarkHole, 5000, 5000);
			timerDarkHoleGrown.cancel();
		}
	};
	
	public TimerDarkHoleGrown(DarkHole darkHole) {
		this.darkHole = darkHole;
		timerDarkHoleGrown = new Timer();
		timerDarkHoleGrown.schedule(timerTaskDarkHoleGrown, 3000, 3000);
		
	}
	
}
