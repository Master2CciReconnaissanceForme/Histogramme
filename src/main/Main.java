package main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import modelProjet.Photos;

import org.opencv.core.Core;

import controller.ConnectInterfaceBDD;
import controller.ConnectInterfaceModel;
import utilBDDProjet.Requetes;

public class Main {
	
	static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args) {

		Requetes DATABASE = new Requetes();
		ConnectInterfaceBDD connecteurBDD = new ConnectInterfaceBDD(DATABASE);
		ConnectInterfaceModel workspace; 
		Vector<Photos> loadsFromBDD;

		/********************************************************************************************************/
		/* Simulation bouton de chargement d'une image */
		
		String nomCommun = "ORCHIDAY";
		String nomScientifique = "Hashtag Include";
		String CheminOptique = "C:/Optique.jpg";
		String CheminThermique = "C:/Thermique.jpg";
		
		loadsFromBDD = connecteurBDD.chargementNewProject(nomCommun, nomScientifique, CheminOptique, CheminThermique);
		workspace = new ConnectInterfaceModel(loadsFromBDD);

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
		connecteurBDD.enregistrerMasque("Hashtag carnaval (Masque - Carnaval vous avez compris ?)", "global");
		
		/* simulation de la superposition du masque sur l'image thermique et affichage + Réaffichage de l'image thermique normale*/
		workspace.afficherImage(workspace.superposerMask(workspace.sourceTh, workspace.masque, true));
		workspace.afficherImage(workspace.sourceTh);

		
		//RecalageTemoin recalageTemoin = new RecalageTemoin("D:/Recalage/cassOp.jpg", "D:/Recalage/cassTh.jpg");
		//RecalageFixe recalageFixe = new RecalageFixe("D:/Recalage/cassOp.jpg", "D:/Recalage/cassTh.jpg");
		//RecalageClassique recalageClassique1 = new RecalageClassique("D:/Recalage/fleurOp.jpg", "D:/Recalage/fleurTh.jpg");
		//RecalageClassique recalageClassique2 = new RecalageClassique("D:/Recalage/cassOp.jpg", "D:/Recalage/cassTh.jpg");
	}
}