package interfaceProjet.Masques;

import javax.swing.JFrame;

public class MasqueJFrame extends JFrame{
	private static final long serialVersionUID = 1L;

	public MasqueJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		MasqueJPanel masques = new MasqueJPanel(this);
		add(masques);
		setVisible(true);
	}
}
