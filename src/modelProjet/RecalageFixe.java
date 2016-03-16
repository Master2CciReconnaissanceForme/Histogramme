package modelProjet;
import java.io.File;

import main.Main;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class RecalageFixe {
	
	
	public Mat sourceOptique 	; 
	public Mat sourceThermique 	; 
	public Mat resizeOptique	;
	public Mat recaleeOptique 	;

	public RecalageFixe(String cheminOptique, String cheminThermique) {
		sourceOptique 	= Highgui.imread(cheminOptique) ;
		sourceThermique = Highgui.imread(cheminThermique) ;
		recaleeOptique = new Mat(sourceThermique.size(), CvType.CV_8UC1);
		
		recalageOptiqueFixe() ;
	}

	private void recalageOptiqueFixe() {
		
		//A FIXER D'APRES RECALAGE TEMOIN BIDOUILLE A LA MAIN, d'apr�s images prises � h = 2m
		double facteur = 0.76 ; 
		int i = 250*3	;
		int j = 147*3 ; 
		
		//Avec ces valeurs d�termin�es UNE FOIS POUR TOUTES, on proc�de aux resize + rognage
		recalageFinalOptique(facteur, i, j);
	}
	
	private void recalageFinalOptique(double facteur, int i, int j) {
		resizeOptique(facteur);
		rognageOptique(i, j);
}

	public void resizeOptique(double facteur) {
		
		resizeOptique = new Mat( (int)(sourceOptique.rows()* facteur),  (int)(sourceOptique.cols()* facteur), CvType.CV_8UC1);
		Size size = new Size((int)(sourceOptique.cols()* facteur) , (int)(sourceOptique.rows()* facteur));
		Imgproc.resize(sourceOptique, resizeOptique, size);
		
		Highgui.imwrite("D:/Recalage/resizeOptique.jpg", resizeOptique);
	}
	
	public void rognageOptique(int iOp, int jOp) {
		for(int iRec = 0 ; iRec < sourceThermique.rows() ; iRec++) {
			for(int jRec = 0 ; jRec < sourceThermique.cols() ; jRec++) {
				recaleeOptique.put(iRec, jRec, resizeOptique.get(iRec + iOp, jRec + jOp));
			}
		}
		//Highgui.imwrite(tmp, recaleeOptique);

		
	}
}
