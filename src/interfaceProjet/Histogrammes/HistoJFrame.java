package interfaceProjet.Histogrammes;

import javax.swing.JFrame;

public class HistoJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public HistoJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		add(new HistoJPanel(this));
		setVisible(true);
	}
}


