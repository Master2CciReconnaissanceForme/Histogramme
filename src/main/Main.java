package main;
import interfaceProjet.InterfacePrincipale;

import java.awt.EventQueue;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import modelProjet.Photos;

import org.opencv.core.Core;

import controller.ConnectInterfaceBDD;
import controller.ConnectInterfaceCalibrage;
import controller.ConnectInterfaceWorkspace;
import utilBDDProjet.Requetes;

public class Main {
	
	public final static String PATHFILE = new File("").getAbsolutePath()+"/tmp";
	public final static String PATHPARAMS = new File("").getAbsolutePath()+"/params.txt";


	static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args) {
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                InterfacePrincipale ex = new InterfacePrincipale();
                ex.setVisible(true);
            }
        });

		Requetes DATABASE = new Requetes();
		ConnectInterfaceBDD connecteurBDD = new ConnectInterfaceBDD(DATABASE);
		ConnectInterfaceWorkspace workspace; 
		Vector<Photos> loadsFromBDD;

		/********************************************************************************************************/
		/* Simulation bouton de chargement d'une image */
		
		String nomCommun = "ORCHIDAY";
		String nomScientifique = "Hashtag Include";
		String CheminOptique = "C:/Optique.jpg";
		String CheminThermique = "C:/Thermique.jpg";
		
		loadsFromBDD = connecteurBDD.chargementNewProject(nomCommun, nomScientifique, CheminOptique, CheminThermique);
		workspace = new ConnectInterfaceWorkspace(loadsFromBDD);

		/* A ce moment là, l'utilisateur a sauvegardé ses images dans la base de données. 
		 * Les objets images sont accessibles depuis l'objet "connecteur" où l'utilisateur peut directement travailler avec
		 */
		
		/********************************************************************************************************/
		/* Simulation de l'affichage des images chargées précédemment et de leurs histogrammes associés*/
		workspace.afficherImage(workspace.sourceOpt);
		workspace.afficherImage(workspace.sourceTh);
		
		workspace.afficherImage(workspace.creerHistogramme(workspace.sourceOptGray, 0, 255));
		workspace.afficherImage(workspace.creerHistogramme(workspace.sourceThGray, 0, 255));

		/********************************************************************************************************/

		/* Simulation de création de masque à partir de l'image thermique et sauvegarde dans la base de données de ces masques*/
		workspace.creerMask(workspace.sourceTh, 254, 255, 2);
		workspace.afficherImage(workspace.masque);
		//connecteurBDD.enregistrerMasque("Carnaval", "global");
		
		/* simulation de la superposition du masque sur l'image thermique et affichage + Réaffichage de l'image thermique normale*/
		workspace.afficherImage(workspace.superposerMask(workspace.sourceTh, workspace.masque, true));
		workspace.afficherImage(workspace.sourceTh);

		/********************************************************************************************************/
		/* simulation de recalibrage par l'utilisateur*/
		
		//	ConnectInterfaceCalibrage.calibrageManuel(workspace.photos.optOrigine, workspace.photos.thOrigine);
		
		/* Simulation de recalibrage automatique */
		ConnectInterfaceCalibrage.calibrageAuto(workspace.photos.optOrigine, workspace.photos.thOrigine);
		
	}
}