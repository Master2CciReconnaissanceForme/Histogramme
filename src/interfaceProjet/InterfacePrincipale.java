package interfaceProjet;

import interfaceProjet.Histogrammes.AffichageHisto;
import interfaceProjet.Histogrammes.AffichageHistoJFrame;
import interfaceProjet.Histogrammes.HistoJFrame;
import interfaceProjet.LoadProjects.NewProjectForm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


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

        JMenu fileMenu = new JMenu("Fichier...");
        JMenu butHist = new JMenu("Histogrammes...");
        JMenu butPrint = new JMenu("Images...");

        JMenuItem newMi = new JMenuItem("New");
        newMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	JFrame frame = new JFrame ("Nouveau projet");
        	    frame.setSize(360, 200);
        	    frame.setVisible(true);
        		NewProjectForm newProjectForm = new NewProjectForm(frame);;
            }
        });

        JMenuItem exitMi = new JMenuItem("Exit");
        exitMi.setToolTipText("Exit application");

        exitMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        JMenuItem Affichage1 = new JMenuItem("Afficher");
        JMenuItem Affichage2 = new JMenuItem("Afficher");

        //file menu
        fileMenu.add(newMi);
        fileMenu.addSeparator();
        fileMenu.add(exitMi);

        butHist.add(Affichage1);
        Affichage1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
            	AffichageHistoJFrame frame = new AffichageHistoJFrame();
        	    frame.setSize(800, 1000);
        	    frame.setVisible(true);
        		AffichageHisto affichageHisto = new AffichageHisto(frame);;
            }
        });
        butPrint.add(Affichage2);
     
        menubar.add(fileMenu);
        menubar.add(butHist);
        menubar.add(butPrint);



        setJMenuBar(menubar);        
    }
}