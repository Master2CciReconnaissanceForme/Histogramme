package interfaceProjet.Calibrage;

import javax.swing.JFrame;

import modelProjet.RecalageFixe;
import modelProjet.RecalageTemoin;

public class ValidationJFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public RecalageFixe recalageTest ;
	public RecalageTemoin recalageTemoin ;

	
	public ValidationJFrame(RecalageFixe recalageTest, RecalageTemoin recalageTemoin) {
		this.recalageTest = recalageTest ;
		this.recalageTemoin = recalageTemoin ;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		ValidationJPanel validation = new ValidationJPanel(recalageTest, recalageTemoin, this);
		add(validation);
		setVisible(true);

	}
	

}
