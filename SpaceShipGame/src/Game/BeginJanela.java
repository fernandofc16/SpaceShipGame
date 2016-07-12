package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BeginJanela extends JPanel {

	private static final long serialVersionUID = 8277146071489360063L;
	private Rectangle startRec = new Rectangle(800, 500, 150, 125);
	private BeginJanela bj = this;
	
	public BeginJanela() {
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(startRec.contains(e.getPoint())) {
					System.out.println("CLICOU");
					MainJanelas.mainJ.changeJPanel(bj);
				}
			}
			
		});
	}
	
	public void paint(Graphics g) {
		g.drawImage(new ImageIcon(this.getClass().getResource("/Images/backGroundSpaceShip.jpg")).getImage(), 0, 0, null);
	}

}
