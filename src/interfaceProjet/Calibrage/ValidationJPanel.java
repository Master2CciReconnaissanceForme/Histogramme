package interfaceProjet.Calibrage;

import interfaceProjet.Utils.NextJDialog;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;
import modelProjet.RecalageFixe;
import modelProjet.RecalageTemoin;

import org.opencv.highgui.Highgui;

import controller.ConnectInterfaceCalibrage;
import controller.ConnectInterfaceWorkspace;

/* Affichage des deux images recalées + superposées
 * "Si vous etes satisfait du calibrage cliquez sur OK sinon, cliquez sur Calibrage manuel"
 * si ok : enregistrement + proposition de masque
 * si calibrage manuel : lancement du recalageTemoin puis rebelote
 */

public class ValidationJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	RecalageFixe recalageTest ;
	RecalageTemoin recalageTemoin ;

	ValidationJFrame validationJFrame ;
	Image recaleeOptiqueIm  ;
	Image recaleeThermiqueIm ;
	
	public ValidationJPanel(final RecalageFixe recalageTest, final RecalageTemoin recalageTemoin, final ValidationJFrame validationJFrame) {
		
		this.validationJFrame = validationJFrame ;
		this.recalageTest = recalageTest ;
		this.recalageTemoin = recalageTemoin ;
		
		JLabel label = new JLabel ("<html><center>"
				+ "<br>Si vous êtes satisfait de ce recalage, cliquez sur \"Valider\". "
				+ "<br>Sinon, cliquez sur \"Recalibrer\" afin de procéder à un calibrage manuel adapté à vos images."
				+"</center></html>");
		add(label);
		
		JButton valider 	= new JButton("Valider");
		JButton recalibrer 	= new JButton("Recalibrer");
		add(valider) ;
		add(recalibrer);
		
		if (recalageTemoin == null) {
			Highgui.imwrite(Main.PATHFILE+"/recaleeOptiqueValid.jpg", recalageTest.recaleeOptique);
			Highgui.imwrite(Main.PATHFILE+"/recaleeThermiqueValid.jpg", recalageTest.sourceThermique);
			recaleeOptiqueIm 	 = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeOptiqueValid.jpg");
			recaleeThermiqueIm = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeThermiqueValid.jpg");	
		}
		else {
			Highgui.imwrite(Main.PATHFILE+"/recaleeOptiqueValid.jpg", recalageTemoin.recaleeOptique);
			Highgui.imwrite(Main.PATHFILE+"/recaleeThermiqueValid.jpg", recalageTemoin.sourceThermique);
			recaleeOptiqueIm   = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeOptiqueValid.jpg");
			recaleeThermiqueIm = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeThermiqueValid.jpg");	
		}
		valider.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Highgui.imwrite(Main.workspace.OUTPUDIR+"/recaleeOptiqueValid.jpg",
						Highgui.imread(Main.PATHFILE+"/recaleeOptiqueValid.jpg"));
				
				Highgui.imwrite(Main.workspace.OUTPUDIR+"/recaleeThermiqueValid.jpg",
						Highgui.imread(Main.PATHFILE+"/recaleeThermiqueValid.jpg"));
				
				new NextJDialog (0);
				validationJFrame.dispose();
				
				/*
				 Pour demande masque
				 
				 Même délire : ouverture d'une JFrame avec les résultat et les boutons valider ou non
				 Si valider on peut encore faire pareil pour choper l'histo de température : une demande si oui ou non on souhaite poursuivre et si oui blablabla
				 Sinon, retour à la page d'accueil
				 
				 */
			}
		});
		
		recalibrer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				validationJFrame.dispose();
				Main.workspace.attribuerRecallageMauel(Main.workspace.photos);
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		
		g2d.drawImage(recaleeThermiqueIm, 
				20, 
				80, 
				recaleeThermiqueIm.getWidth(this)/3, 
				recaleeThermiqueIm.getHeight(this)/3, 
				this);
		
		g2d.drawImage(recaleeOptiqueIm, 
				20, 
				recaleeThermiqueIm.getHeight(this)/3 + 90, 
				recaleeOptiqueIm.getWidth(this)/3, 
				recaleeOptiqueIm.getHeight(this)/3, 
				this);

		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
		
		g2d.drawImage(recaleeThermiqueIm, 
				recaleeThermiqueIm.getWidth(this)/3  + 30, 
				80, 
				recaleeThermiqueIm.getWidth(this)/3, 
				recaleeThermiqueIm.getHeight(this)/3, 
				this);
		
		g2d.drawImage(recaleeOptiqueIm, 
				recaleeThermiqueIm.getWidth(this)/3  + 30, 
				80, 
				recaleeOptiqueIm.getWidth(this)/3, 
				recaleeOptiqueIm.getHeight(this)/3, 
				this);	
	}
}

