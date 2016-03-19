package interfaceProjet.LoadProjects;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;

public class FormUtility {
    /**
     * Grid bag constraints for fields and labels
     */
    private GridBagConstraints lastConstraints = null;
    private GridBagConstraints middleConstraints = null;
    private GridBagConstraints labelConstraints = null;
    private GridBagConstraints buttonConstraints = null;


    public FormUtility() {
   
        lastConstraints =  new GridBagConstraints();
        lastConstraints.fill = GridBagConstraints.HORIZONTAL;
        lastConstraints.anchor = GridBagConstraints.NORTHWEST;
        lastConstraints.weightx = 1.0;
        lastConstraints.gridwidth = GridBagConstraints.REMAINDER;
        lastConstraints.insets = new Insets(1, 1, 1, 1);
        

        labelConstraints = (GridBagConstraints) lastConstraints.clone();
        lastConstraints.anchor = GridBagConstraints.BASELINE;

        labelConstraints.weightx = 0.0;
        labelConstraints.gridwidth = 1;
        
        buttonConstraints = (GridBagConstraints) lastConstraints.clone();
        buttonConstraints.gridwidth = GridBagConstraints.RELATIVE;
        buttonConstraints.weightx = 0.0;
        buttonConstraints.gridwidth = 1;
        buttonConstraints.insets = new Insets(15, 5, 0, 0);
    }


    public void addLast(Component c, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, lastConstraints);
        parent.add(c);
    }
    
  
    public void addLabel(Component c, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, labelConstraints);
        parent.add(c);
    }
  
    public JLabel addLabel(String s, Container parent) {
        JLabel c = new JLabel(s);
        addLabel(c, parent);
        return c;
    }
    
    public void addButton(Component button, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(button, buttonConstraints);
        parent.add(button);
    }

  

}