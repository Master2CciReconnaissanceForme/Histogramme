package controller;

import java.util.Date;
import java.util.Vector;

import modelProjet.Photos;
import utilBDDProjet.Requetes;

public class ConnectInterfaceBDD {
	
	static Requetes DATABASE;
	
	public ConnectInterfaceBDD(Requetes DATABASE) {
		this.DATABASE = DATABASE;
	}
	
	public static Vector<Photos> chargementNewProject(String nomCommun, String nomScientifique, String cheminOpt, String cheminTh ) {
		if (nomCommun.isEmpty() || cheminOpt.isEmpty() || cheminTh.isEmpty()){
			System.out.println("Donnée manquante - Réessayez");
			return null;
		}
		
		if(nomScientifique.isEmpty())
			nomScientifique = null;
		
		if(Requetes.nouvellePlante(nomCommun, nomScientifique))
			System.out.println("Nouvelle Plante enregistrée");

		Date datephoto = new Date();
		DATABASE.savePhotoOrigine(DATABASE.dernierIdPlante(), cheminOpt, cheminTh, datephoto);
		
		return DATABASE.listePhotos(DATABASE.dernierIdPlante());
	}
}
