package main;
import interfaceProjet.InterfacePrincipale;

import java.awt.EventQueue;
import java.io.File;
import java.util.Vector;

import modelProjet.Photos;

import org.opencv.core.Core;

import utilBDDProjet.Requetes;
import controller.ConnectInterfaceBDD;
import controller.ConnectInterfaceWorkspace;

public class Main {
	
	public final static String PATHFILE = new File("").getAbsolutePath()+"/tmp";
	public final static String PATHPARAMS = new File("").getAbsolutePath()+"/params.txt";
	
	
	public static Requetes DATABASE = new Requetes();
	public static ConnectInterfaceBDD connecteurBDD = new ConnectInterfaceBDD(DATABASE);
	public static ConnectInterfaceWorkspace workspace; 
	public static Vector<Photos> loadsFromBDD;
	
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


		/********************************************************************************************************/
		/* Simulation de l'affichage des images chargées précédemment et de leurs histogrammes associés*/
		/*workspace.afficherImage(workspace.sourceOpt);
		workspace.afficherImage(workspace.sourceTh);
		
		workspace.afficherImage(workspace.creerHistogramme(workspace.sourceOptGray, 0, 255));
		workspace.afficherImage(workspace.creerHistogramme(workspace.sourceThGray, 0, 255));*/

		/********************************************************************************************************/

		/* Simulation de création de masque à partir de l'image thermique et sauvegarde dans la base de données de ces masques*/
		/*workspace.creerMask(workspace.sourceTh, 254, 255, 2);
		workspace.afficherImage(workspace.masque);
		connecteurBDD.enregistrerMasque("Carnaval", "global");
		
		/* simulation de la superposition du masque sur l'image thermique et affichage + Réaffichage de l'image thermique normale*/
		/*workspace.afficherImage(workspace.superposerMask(workspace.sourceTh, workspace.masque, true));
		workspace.afficherImage(workspace.sourceTh);*/

		/********************************************************************************************************/
		/* simulation de recalibrage par l'utilisateur*/
		
		//	ConnectInterfaceCalibrage.calibrageManuel(workspace.photos.optOrigine, workspace.photos.thOrigine);
		
		/* Simulation de recalibrage automatique */
		//ConnectInterfaceCalibrage.calibrageAuto(workspace.photos.optOrigine, workspace.photos.thOrigine);
		
	}
}