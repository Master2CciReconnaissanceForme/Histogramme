package interfaceProjet.Utils;
import interfaceProjet.Histogrammes.HistoJFrame;
import interfaceProjet.Masques.MasqueJFrame;
import interfaceProjet.Messages.TitleMessages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NextJDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	int etape ;

	public NextJDialog(final int etape) {
		super();
		this.etape = etape ;
		setTitle("Que souhaitez-vous faire ?");
		setSize(350, 150);
		JPanel panel 	= new JPanel();
		JButton oui 	= new JButton("Oui") ;
		JButton non 	= new JButton("Non") ;
		JLabel choix ;
		
		if (etape == 0) 
			choix 	= new JLabel (TitleMessages.CREATEMASQUE);
		else 
			choix 	= new JLabel (TitleMessages.CREATEHISTOGRAMME);
		
		add(panel);
		panel.add(choix);
		panel.add(oui);		
		panel.add(non);		
		setVisible(true);
		oui.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				if (etape == 0 )
					new MasqueJFrame();
				else 
					new HistoJFrame();
			}
		});
		
		non.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
