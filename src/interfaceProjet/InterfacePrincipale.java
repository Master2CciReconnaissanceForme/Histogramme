package interfaceProjet;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;


public class InterfacePrincipale extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InterfacePrincipale() {
        
        initUI();
    }

    private void initUI() {

        createMenuBar();

        setTitle("Logiciel traitement d'imagerie");
        setSize(400, 60);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void createMenuBar() {
        
        JMenuBar menubar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu but1Menu = new JMenu("Recent");
        
                
        JMenu but2Menu = new JMenu("Touch2");
        JMenu but3Menu = new JMenu("Touch3");



        JMenu impMenu = new JMenu("Import");

        JMenuItem newsfMi = new JMenuItem("Import newsfeed list...");
        JMenuItem bookmMi = new JMenuItem("Import bookmarks...");
        JMenuItem mailMi = new JMenuItem("Import mail...");

        impMenu.add(newsfMi);
        impMenu.add(bookmMi);
        impMenu.add(mailMi);

        JMenuItem newMi = new JMenuItem("New");
        JMenuItem openMi = new JMenuItem("Open");
        
        JMenuItem saveMi = new JMenuItem("Save");

        JMenuItem exitMi = new JMenuItem("Exit");
        exitMi.setToolTipText("Exit application");

        exitMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        // ajout des buttons check
        JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
        JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("On/Off Button1");
        JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem("On/Off Button2");

        //file menu
        fileMenu.add(newMi);
        fileMenu.add(openMi);
        fileMenu.add(saveMi);
        fileMenu.addSeparator();
        fileMenu.add(impMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMi);

        //menu bar
        menubar.add(fileMenu);
        menubar.add(but1Menu);
        menubar.add(but2Menu);
        menubar.add(but3Menu);
        
        //Check Button
        fileMenu.add(checkAction);
        but2Menu.add(radioAction1);
        but2Menu.add(radioAction2);


        setJMenuBar(menubar);        
    }
}