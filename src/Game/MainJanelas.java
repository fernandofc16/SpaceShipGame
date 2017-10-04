package Game;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainJanelas extends JFrame {
	
	public static MainJanelas mainJ;
	public static Fase faseJ;
	public static SpaceShip spaceShip;
	public static BeginJanela beginJanela;
	
	public MainJanelas() {
		spaceShip = new SpaceShip();
		beginJanela = new BeginJanela();
		setTitle("SpaceShipGame");
		setSize(1200,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		add(beginJanela);
		setVisible(true);
	}

	public void resetGame() {
		faseJ.setHorda(1);
		faseJ.getDarkHoles().clear();
		faseJ.getInimigos().clear();
		faseJ.getBullets().clear();
		faseJ.getBossShots().clear();
		mainJ.dispose();
		mainJ = new MainJanelas();
	}
	
	public void changeJPanel(JPanel jp) {
		mainJ.getContentPane().removeAll();
		faseJ = new Fase();
		mainJ.getContentPane().add(faseJ);
		validate();
		faseJ.requestFocusInWindow();
	}
	
	private static final long serialVersionUID = 3902741870615585187L;
	
	public static void main(String[] args) {
		mainJ = new MainJanelas();
	}
}
