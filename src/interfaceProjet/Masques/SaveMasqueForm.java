package interfaceProjet.Masques;

import interfaceProjet.LoadProjects.FormUtility;
import interfaceProjet.Utils.NextJDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Main;

import org.opencv.highgui.Highgui;

public class SaveMasqueForm {
	
	JFrame frame ;	
	MasqueJFrame masqueJFrame ;
	
	public SaveMasqueForm(MasqueJFrame masqueJFrame, JFrame frame) {	
		
		this.frame = frame ;
		frame.setSize(400, 200);
		this.masqueJFrame = masqueJFrame ;

		JPanel form = new JPanel();

	    frame.getContentPane().setLayout(new BorderLayout());
	    frame.getContentPane().add(form, BorderLayout.NORTH);
	    
	    form.setLayout(new GridBagLayout());
	    FormUtility formUtility = new FormUtility();
	    
	    constructForm(form, formUtility);
	    
	    form.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
	private void constructForm(JPanel form, FormUtility formUtility) {

	    final JTextField nomMasque 		 = new JTextField();
	    final JComboBox<String> categorie = new JComboBox<String>();

	   
        JButton ok 		= new JButton("Valider");
        JButton cancel 	= new JButton("Annuler");
	    
        formUtility.addLabel("Nom du masque : ", form);
	    formUtility.addLast(nomMasque, form);
	    
	    formUtility.addLabel("Catégorie : ", form);
	    formUtility.addLast(categorie, form);
	    
	    categorie.addItem("Global");
	    categorie.addItem("Tige") ;
	    categorie.addItem("Feuille") ;
	    categorie.addItem("Fleur") ;
	    
	    formUtility.addButton(ok, form);
	    formUtility.addButton(cancel, form);

	 	ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!nomMasque.getText().equals("") && !categorie.getSelectedItem().equals(""))  {				
					
					Highgui.imwrite(Main.workspace.OUTPUDIR+"/"+nomMasque.getText()+".jpg", Main.workspace.masque);
					Main.connecteurBDD.enregistrerMasque(Main.workspace.OUTPUDIR+"/"+nomMasque.getText()+".jpg", categorie.getSelectedItem().toString());
					
					frame.dispose() ;
					masqueJFrame.dispose() ;
					new NextJDialog(1) ;
				}
				
				else {
					if (nomMasque.getText().equals(""))
						nomMasque.setBackground(new Color (254, 231, 240));
					else 
						nomMasque.setBackground(Color.WHITE);
					
					if (categorie.getSelectedItem().equals(""))
						categorie.setBackground(new Color (254, 231, 240));
				}
			}
		});
        
	 	
	 	cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
	 	
		frame.setVisible(true);

	}
	
}




