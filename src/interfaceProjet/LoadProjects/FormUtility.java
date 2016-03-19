package interfaceProjet.LoadProjects;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;

public class FormUtility {

    private GridBagConstraints lastConstraints 	 = null ;
    private GridBagConstraints simpleConstraints  = null ;
    private GridBagConstraints buttonConstraints = null ;

    public FormUtility() {
    	initLastConstraints();
    	initSimpleCOnstraints();
    	initButtonConstraints();
    }

    /* Méthodes d'initialisations des GridBagConstraints */
    
    private void initButtonConstraints() {       
        buttonConstraints = (GridBagConstraints) lastConstraints.clone();
        buttonConstraints.gridwidth = GridBagConstraints.RELATIVE;
        buttonConstraints.weightx = 0.0;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.insets = new Insets(15, 5, 0, 0);		
	}

	private void initSimpleCOnstraints() {
        simpleConstraints = (GridBagConstraints) lastConstraints.clone();
        simpleConstraints.weightx = 0.0;
        simpleConstraints.gridwidth = 1;		
	}

	private void initLastConstraints() {
		lastConstraints =  new GridBagConstraints();
        lastConstraints.fill = GridBagConstraints.HORIZONTAL;
        lastConstraints.anchor = GridBagConstraints.NORTHWEST;
        lastConstraints.weightx = 1.0;
        lastConstraints.gridwidth = GridBagConstraints.REMAINDER;
        lastConstraints.insets = new Insets(1, 1, 1, 1);
	}

	/* Méthodes du FormUtility pour ajout des éléments dans le futur formulaire */

	public void addLast(Component c, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, lastConstraints);
        parent.add(c);
    }
    
    public void addSimpleComponent(Component c, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, simpleConstraints);
        parent.add(c);
    }
  
    public JLabel addLabel(String s, Container parent) {
        JLabel c = new JLabel(s);
        addSimpleComponent(c, parent);
        return c;
    }
    
    public void addButton(Component button, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(button, buttonConstraints);
        parent.add(button);
    }
}