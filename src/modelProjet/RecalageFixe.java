package modelProjet;
import java.io.BufferedReader;
import java.io.FileReader;

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
		String [] content = new String[3] ; //pour récupérer les 3 lignes du fichier

		try {
			int i = 0 ;
			String line ;
			BufferedReader fileReader = new BufferedReader(new FileReader(Main.PATHPARAMS));
			while ((line = fileReader.readLine()) != null) {
			content[i] = line ;
			i++;
			}
			fileReader.close();
		} 
		catch (Exception e) { 
			e.printStackTrace();
		}    
		double facteur = Double.parseDouble(content[0]); 
		int i = Integer.parseInt(content[1]);
		int j = Integer.parseInt(content[2]);
		recalageFinalOptique(facteur, i, j);
		Highgui.imwrite(Main.PATHFILE+"/recaleeOptique.jpg", recaleeOptique);
	}
	
	private void recalageFinalOptique(double facteur, int i, int j) {
		resizeOptique(facteur);
		rognageOptique(i, j);
	}

	public void resizeOptique(double facteur) {
		
		resizeOptique = new Mat( (int)(sourceOptique.rows()* facteur),  (int)(sourceOptique.cols()* facteur), CvType.CV_8UC1);
		Size size = new Size((int)(sourceOptique.cols()* facteur) , (int)(sourceOptique.rows()* facteur));
		Imgproc.resize(sourceOptique, resizeOptique, size);
		
		Highgui.imwrite(Main.PATHFILE+"/resizeOptique.jpg", resizeOptique);
	}
	
	public void rognageOptique(int iOp, int jOp) {
		for(int iRec = 0 ; iRec < sourceThermique.rows() ; iRec++) {
			for(int jRec = 0 ; jRec < sourceThermique.cols() ; jRec++) {
				recaleeOptique.put(iRec, jRec, resizeOptique.get(iRec + iOp, jRec + jOp));
			}
		}
	}
}
