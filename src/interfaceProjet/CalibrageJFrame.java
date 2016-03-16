package interfaceProjet;

import javax.swing.JFrame;

import modelProjet.RecalageTemoin;

import org.opencv.core.Mat;

public class CalibrageJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	RecalageTemoin recalageTemoin ;
	
	public CalibrageJFrame(RecalageTemoin recalageTemoin) {
		this.recalageTemoin = recalageTemoin ;
		setSize(800, 800);
		CalibrageJPanel imJPanel = new CalibrageJPanel (this) ;
		add(imJPanel);
	}

}
