package controller;

import java.io.File;
import java.util.Date;
import java.util.Vector;

import main.Main;
import modelProjet.Photos;

import org.opencv.core.Mat;

import utilBDDProjet.Requetes;

public class ConnectInterfaceBDD {
	
	static Requetes DATABASE;
	static java.util.Date datephoto;
	
	public ConnectInterfaceBDD(Requetes DATABASE) {
		this.DATABASE = DATABASE;
		datephoto = new Date();
	}
	
	public static Vector<Photos> chargementNewProject(String nomCommun, String nomScientifique, String cheminOpt, String cheminTh ) {
		if (nomCommun.isEmpty() || cheminOpt.isEmpty() || cheminTh.isEmpty()){
			System.out.println("Donnée manquante - Réessayez");
			return null;
		}
		
		if(nomScientifique.isEmpty())
			nomScientifique = null;
		
		if(Requetes.nouvellePlante(nomScientifique, nomCommun))
			System.out.println("Nouvelle Plante enregistrée");
		
		DATABASE.savePhotoOrigine(DATABASE.dernierIdPlante(), cheminOpt, cheminTh, datephoto);

		return DATABASE.listePhotos(DATABASE.dernierIdPlante());
	}
	
	public static void sauvegardeNewProject(){
		String cheminOpt = Main.PATHYGGDRASIL +"/"+DATABASE.dernierIdPlante()+"/optique.jpg";
		String cheminTh = Main.PATHYGGDRASIL +"/"+DATABASE.dernierIdPlante()+"/thermique.jpg";
	}
	
	public static void enregistrerMasque(String masque, String typeMasque){
		DATABASE.saveMasque(datephoto, masque, typeMasque);
	}
	
	public static void enregistrerRecallage(String oprecal, String threcal){
		DATABASE.saveOpticRecal(datephoto, oprecal);
		DATABASE.saveThermiRecal(datephoto, threcal);
	}
}
