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
		ConnectInterfaceModel connecteurModel; 
		Vector<Photos> workspace;

		/********************************************************************************************************/
		/* Simulation bouton de chargement d'une image */
		
		String nomCommun = "ORCHIDAY";
		String nomScientifique = "Hashtag Include";
		String CheminOptique = "C:/Optique.jpg";
		String CheminThermique = "C:/Thermique.jpg";
		
		workspace = connecteurBDD.chargementNewProject(nomCommun, nomScientifique, CheminOptique, CheminThermique);
		connecteurModel = new ConnectInterfaceModel(workspace);

		/* A ce moment là, l'utilisateur a sauvegardé ses images dans la base de données. 
		 * Les objets images sont accessibles depuis l'objet "connecteur" où l'utilisateur peut directement travailler avec
		 */
		
		/********************************************************************************************************/
		/* Simulation de l'affichage des images chargées précédemment et de leurs histogrammes associés*/
		connecteurModel.afficherImage(connecteurModel.sourceOpt);
		connecteurModel.afficherImage(connecteurModel.sourceTh);
		
		connecteurModel.afficherImage(connecteurModel.creerHistogramme(connecteurModel.sourceOptGray, 0, 255));
		connecteurModel.afficherImage(connecteurModel.creerHistogramme(connecteurModel.sourceThGray, 0, 255));

		/********************************************************************************************************/

		/* Simulation de création de masque à partir de l'image thermique et sauvegarde dans la base de données de ces masques*/
		/** TODO **/
		}
}