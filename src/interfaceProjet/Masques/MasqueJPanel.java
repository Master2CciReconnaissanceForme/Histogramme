package interfaceProjet.Masques;

import interfaceProjet.Messages.TitleMessages;
import interfaceProjet.Utils.NextJDialog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Main;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class MasqueJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	MasqueJFrame masqueJFrame ;

	private Image masque ;
	private Image masqueOptique ;
	private Mat masqueOptiqueMat ;
	private Image masqueThermique ;
	private Mat masqueThermiqueMat ;
	/* Simulation de création de masque à partir de l'image thermique et sauvegarde dans la base de données de ces masques*/
	/*workspace.creerMask(workspace.sourceTh, 254, 255, 2);
	workspace.afficherImage(workspace.masque);
	connecteurBDD.enregistrerMasque("Carnaval", "global");
	
	/* simulation de la superposition du masque sur l'image thermique et affichage + Réaffichage de l'image thermique normale*/
	/*workspace.afficherImage(workspace.superposerMask(workspace.sourceTh, workspace.masque, true));
	workspace.afficherImage(workspace.sourceTh);*/
	
	
	public MasqueJPanel(final MasqueJFrame masqueJFrame) {
		this.masqueJFrame = masqueJFrame ;
		
		Main.workspace.creerMask(Main.workspace.sourceTh, 254, 255, 2);
		Highgui.imwrite(Main.PATHFILE+"/masqueGlobal.jpg", Main.workspace.masque);
		masque = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/masqueGlobal.jpg");
		
		masqueThermiqueMat = Main.workspace.superposerMask(Main.workspace.sourceTh, Main.workspace.masque, true) ;
		Highgui.imwrite(Main.PATHFILE+"/masqueThermique.jpg", masqueThermiqueMat);
		masqueThermique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/masqueThermique.jpg");
		
		masqueOptiqueMat = Main.workspace.superposerMask(Main.workspace.optRecal, Main.workspace.masque, true) ;
		Highgui.imwrite(Main.PATHFILE+"/masqueOptique.jpg", masqueOptiqueMat);
		masqueOptique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/masqueOptique.jpg");
							
		add(new JLabel (TitleMessages.SAVEMASQUE));
		JButton save 	= new JButton("Sauvegarder");
		JButton cancel 	= new JButton("Annuler");
		add(save) ;
		add(cancel);
	
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				new SaveMasqueForm(masqueJFrame, new JFrame("Enregistrement du masque"));
			}
		});
		
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				masqueJFrame.dispose();
			}
		});
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(masque, 
				20, 
				80, 
				masque.getWidth(this)/3, 
				masque.getHeight(this)/3, 
				this);
		
		g.drawImage(masqueOptique, 
				20 + masque.getWidth(this)/3 + 10 , 
				80, 
				masqueOptique.getWidth(this)/3, 
				masqueOptique.getHeight(this)/3, 
				this);
	
		g.drawImage(masqueThermique, 
				20 + masque.getWidth(this)/3 + 10 , 
				80 + masqueOptique.getHeight(this)/3 + 10, 
				masqueThermique.getWidth(this)/3, 
				masqueThermique.getHeight(this)/3, 
				this);
	}
}
