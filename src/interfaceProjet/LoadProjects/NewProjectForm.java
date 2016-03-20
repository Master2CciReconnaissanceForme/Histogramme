package interfaceProjet.LoadProjects;

import interfaceProjet.Messages.HelpJDialog;
import interfaceProjet.Utils.UtilFiles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.ConnectInterfaceWorkspace;
import main.Main;

public class NewProjectForm {
	
	JFrame frame ;
	
	public String nomCommunValue 		= "";
	public String nomScientifiqueValue 	= "";
	public String cheminThermiqueValue 	= "";
	public String cheminOptiqueValue	= "" ;
	
	public NewProjectForm(JFrame frame) {	//la frame induite par le clic sur "nouveau projet" (cf main) est passée en paramètre
		
		this.frame = frame ;
		JPanel form = new JPanel();

	    frame.getContentPane().setLayout(new BorderLayout());
	    frame.getContentPane().add(form, BorderLayout.NORTH);
	    
	    form.setLayout(new GridBagLayout());
	    FormUtility formUtility = new FormUtility();
	    
	    constructForm(form, formUtility);
	    
	    form.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
	private void constructForm(JPanel form, FormUtility formUtility) {

	    final JTextField nomCommun 		 = new JTextField();
	    final JTextField nomScientifique = new JTextField();
	    final JTextField cheminOptique 	 = new JTextField();
	    final JTextField cheminThermique = new JTextField();

	    JButton browse1 = new JButton("Parcourir");
	    JButton browse2 = new JButton("Parcourir");
        JButton cancel 	= new JButton("Annuler");
        JButton ok 		= new JButton("Valider");
        JButton help 	= new JButton("Aide");
	    
        addNamesForm(nomCommun, nomScientifique, form, formUtility);
        addBrowsesForm(cheminOptique, browse1, cheminThermique, browse2, form, formUtility);
        addButtonsForm(cancel, ok, help, form, formUtility);
                
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
				
				if (!nomCommun.getText().equals("") && !cheminOptique.getText().equals("") && !cheminThermique.getText().equals("") ) {				
					Main.workspace = new ConnectInterfaceWorkspace(nomCommun.getText(), nomScientifique.getText(),
							cheminOptique.getText(), cheminThermique.getText(), Main.DATABASE.dernierIdPlante()+1);
						
					frame.dispose();
				}
				
				else {
					if (nomCommun.getText().equals(""))
						nomCommun.setBackground(new Color (254, 231, 240));
					else 
						nomCommun.setBackground(Color.WHITE);
					
					if (cheminOptiqueValue.equals(""))
						cheminOptique.setBackground(new Color (254, 231, 240));
					
					if (cheminThermiqueValue.equals(""))
						cheminThermique.setBackground(new Color (254, 231, 240));
				}
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
  
	}

	private void addButtonsForm(JButton cancel, JButton ok, JButton help, JPanel form, FormUtility formUtility) {
		addButtonForm(cancel, form, formUtility);
        addButtonForm(ok, form, formUtility);
        addButtonForm(help, form, formUtility);		
	}

	private void addBrowsesForm(JTextField cheminOptique, JButton browse1, JTextField cheminThermique, JButton browse2,
			JPanel form, FormUtility formUtility) {
		addBrowseForm("* Image optique : ", cheminOptique, form, formUtility, browse1);
        addBrowseForm("* Image thermique : ", cheminThermique, form, formUtility, browse2);		
	}

	private void addNamesForm(JTextField nomCommun, JTextField nomScientifique, JPanel form, FormUtility formUtility) {
		addNameForm("* Nom commun :", nomCommun, form, formUtility);
		addNameForm("  Nom scientifique :", nomScientifique, form, formUtility);		
	}

	private void addButtonForm(JButton button, JPanel form, FormUtility formUtility) {
		Dimension buttonSize = button.getPreferredSize();
		buttonSize.width = 100;
		button.setPreferredSize(buttonSize);
		formUtility.addButton(button, form);		
	}

	private void addBrowseForm(String string, JTextField textField, JPanel form, FormUtility formUtility, JButton button) {
		formUtility.addLabel(string, form);
		formUtility.addSimpleComponent(textField, form);
		Dimension browseSize = button.getPreferredSize();
		browseSize.width = 60;
		button.setPreferredSize(browseSize);
		formUtility.addLast(button, form);		
	}

	private void addNameForm(String string, JTextField textField, JPanel form, FormUtility formUtility) {
		formUtility.addLabel(string, form);
	    formUtility.addLast(textField, form);		
	}
}




