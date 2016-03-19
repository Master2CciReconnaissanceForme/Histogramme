package interfaceProjet.LoadProjects;
import interfaceProjet.helpMessages.HelpJDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import utilBDDProjet.Requetes;
import main.Main;
import modelProjet.Photos;
import controller.ConnectInterfaceBDD;
import controller.ConnectInterfaceWorkspace;
import interfaceProjet.Utils.UtilFiles;

public class NewProjectForm {
	
	JFrame frame ;
	
	public String nomCommunValue = "";
	public String nomScientifiqueValue = "";
	public String cheminThermiqueValue = "";
	public String cheminOptiqueValue= "" ;
	
	public NewProjectForm(JFrame frame) {	//la frame induite par le clic sur "nouveau projet" est pass� en param�tre
		this.frame = frame ;
		constructForm();
	}
	
	private void constructForm() {
	
	    JPanel form = new JPanel();
	    frame.getContentPane().setLayout(new BorderLayout());
	    frame.getContentPane().add(form, BorderLayout.NORTH);
	    
	    form.setLayout(new GridBagLayout());
	    FormUtility formUtility = new FormUtility();

	    formUtility.addLabel("* Nom commun : ", form);
	    final JTextField nomCommun = new JTextField();
	    formUtility.addLast(nomCommun, form);
	    
	    formUtility.addLabel("Nom scientifique : ", form);
	    final JTextField nomScientifique = new JTextField();
	    formUtility.addLast(nomScientifique, form);
	    
	    formUtility.addLabel("* Image optique : ", form);
	    final JTextField cheminOptique = new JTextField();
	    formUtility.addLabel(cheminOptique, form);
	    
	    JButton browse1 = new JButton("Parcourir");
	    Dimension browseSize = browse1.getPreferredSize();
	    browseSize.width = 60;
	    browse1.setPreferredSize(browseSize);
	    formUtility.addLast(browse1, form);

	    formUtility.addLabel("* Image thermique : ", form);
	    final JTextField cheminThermique = new JTextField();
	    formUtility.addLabel(cheminThermique, form);
	    
	   
	    JButton browse2 = new JButton("Parcourir");
	    Dimension browse2Size = browse2.getPreferredSize();
	    browse2Size.width = 60;
	    browse2.setPreferredSize(browse2Size);
	    formUtility.addLast(browse2, form);
	    
        JButton cancel = new JButton("Annuler");
        Dimension cancelSize = cancel.getPreferredSize();
        cancelSize.width = 100;
        cancel.setPreferredSize(cancelSize);
        
        JButton ok = new JButton("Valider");
        Dimension okSize = ok.getPreferredSize();
        okSize.width = 100;
        ok.setPreferredSize(okSize);
  
        JButton help = new JButton("Aide");
        Dimension helpSize = help.getPreferredSize();
        helpSize.width = 100;
        help.setPreferredSize(okSize);
        
        formUtility.addButton(cancel, form);
        formUtility.addButton(ok, form);
        formUtility.addButton(help, form);
             
        browse1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cheminOptique.setText(UtilFiles.ParcourirFiles());
				cheminOptiqueValue = cheminOptique.getText();
				if (!cheminOptiqueValue.equals(""))
					cheminOptique.setBackground(Color.WHITE);
				
			}
		});
        
        browse2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				cheminThermique.setText(UtilFiles.ParcourirFiles());
				cheminThermiqueValue = cheminThermique.getText();
				if (!cheminThermiqueValue.equals(""))
					cheminThermique.setBackground(Color.WHITE);
				
			}
		});

	 	ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// r�cup�ration des 4 donn�es si obligatoires remplies
				// insistance sur les obligatoires sinon
				
				if (!nomCommun.getText().equals("") && !cheminOptique.getText().equals("") && !cheminThermique.getText().equals("") ) {
					System.out.println("ok tout rempli");
					
					Main.loadsFromBDD = Main.connecteurBDD.chargementNewProject(nomCommun.getText(),nomScientifique.getText(), cheminOptique.getText(), cheminThermique.getText());
					Main.workspace = new ConnectInterfaceWorkspace(Main.loadsFromBDD);		
					frame.dispose();
				}
				
				else
					if (nomCommun.getText().equals(""))
						nomCommun.setBackground(new Color (254, 231, 240));
					if (cheminOptiqueValue.equals(""))
						cheminOptique.setBackground(new Color (254, 231, 240));
					if (cheminThermiqueValue.equals(""))
						cheminThermique.setBackground(new Color (254, 231, 240));
			}
				
		});
        
	 	
	 	cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();				
			}
		});

        help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HelpJDialog helpJDialog = new HelpJDialog(frame, "Aide", true);
				helpJDialog.setVisible(true);
			}
		});
  
	    form.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
}
