package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TryAgainJanela extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3890540528106284893L;
	private JButton tryAgain;
	
	public TryAgainJanela() {
		
		tryAgain = new JButton("Try Again");
		tryAgain.addActionListener(this);
		setSize(100,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(tryAgain);
		setVisible(false);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == tryAgain) {
			MainJanelas.mainJ.resetGame();
			setVisible(false);
		}
		
	}

}
