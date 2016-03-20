package interfaceProjet.Histogrammes;

import interfaceProjet.Messages.TitleMessages;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class HistoJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	HistoJFrame histoJFrame ;

	Image masqueThermique ;
	Image histogrammeGlobal ;
	Mat masqueTh;
	public HistoJPanel(final HistoJFrame histoJFrame) {
		this.histoJFrame = histoJFrame ;
	
		masqueTh = Main.workspace.superposerMask(Main.workspace.sourceTh, Main.workspace.masque, true) ;
		Highgui.imwrite(Main.PATHFILE+"/masqueThermique.jpg", masqueTh);
		masqueThermique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/masqueThermique.jpg");

		// Pour les max min je te laisse voir ce qu'il faut mettre 
		Mat histoGlobal = Main.workspace.creerHistogramme(masqueTh, 1, 255);
		Imgproc.resize(histoGlobal, histoGlobal,masqueTh.size());
		Highgui.imwrite(Main.PATHFILE+"/histogrammeTGlobal.jpg", histoGlobal);
		histogrammeGlobal = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/histogrammeTGlobal.jpg");
		
		add(new JLabel (TitleMessages.ENDHISTOGRAMME));
		JButton save 	= new JButton("Sauvegarder");
		JButton cancel 	= new JButton("Annuler");
		add(save) ;
		add(cancel);
	
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				// ENREGISTREMENT JOHN
				
				histoJFrame.dispose();		 
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				histoJFrame.dispose();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(masqueThermique, 
				20, 
				80, 
				masqueThermique.getWidth(this)/3, 
				masqueThermique.getHeight(this)/3, 
				this);
		
		g.drawImage(histogrammeGlobal, 
				20 + masqueThermique.getWidth(this)/3 + 10 , 
				80, 
				histogrammeGlobal.getWidth(this)/3, 
				histogrammeGlobal.getHeight(this)/3, 
				this);
	}
	

}
