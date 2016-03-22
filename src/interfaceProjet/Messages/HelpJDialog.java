package interfaceProjet.Messages;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpJDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public JFrame jFrame ;
	
	public HelpJDialog(JFrame jFrame, String title, boolean modal) {
		
		super(jFrame, title, modal);
		setSize(500, 250);
		
		JPanel help = new JPanel() ;
		add(help);
		JLabel text = new JLabel(HelpMessages.NEWPROJECT);
		help.add(text);
	}
}
