package modelProjet;
import java.io.File;

import interfaceProjet.CalibrageJFrame;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/* /!\ /!\ /!\ ROTATION NON PRISE EN COMPTE...  /!\ /!\ /!\ */

public class RecalageTemoin {
	
	public Mat sourceOptique 	; 
	public Mat sourceThermique 	; 
	public Mat resizeOptique	;
	public Mat recaleeOptique 	;

	public RecalageTemoin(String cheminOptique, String cheminThermique) {
		sourceOptique 	= Highgui.imread(cheminOptique) ;
		sourceThermique = Highgui.imread(cheminThermique) ;
		recaleeOptique = new Mat(sourceThermique.size(), CvType.CV_8UC1);
		recalageGraphiqueOptique();
	}
	
	
	private void recalageGraphiqueOptique() {
		CalibrageJFrame imJFrame = new CalibrageJFrame(this)	;
		imJFrame.setVisible(true);

	}

	public void recalageFinalOptique(double facteur, int i, int j) {
			resizeOptique(facteur);
			rognageOptique(i, j);
	}
	
	public void resizeOptique(double facteur) {
		
		resizeOptique = new Mat( (int)(sourceOptique.cols()* facteur),  (int)(sourceOptique.rows()* facteur), CvType.CV_8UC1);
		Size size = new Size((int)(sourceOptique.cols()* facteur) , (int)(sourceOptique.rows()* facteur));
		
		Imgproc.resize(sourceOptique, resizeOptique, size);
		String pathFile = new File("").getAbsolutePath()+"/tmp";
		Highgui.imwrite(pathFile+"/resizeOptique.jpg", resizeOptique);
	}
	
	public void rognageOptique(int iOp, int jOp) {
		
		for(int iRec = 0 ; iRec < sourceThermique.rows() ; iRec++) {
			for(int jRec = 0 ; jRec < sourceThermique.cols() ; jRec++) {
				recaleeOptique.put(iRec, jRec, resizeOptique.get(iRec + iOp, jRec + jOp));
			}
		}
		String pathFile = new File("").getAbsolutePath()+"/tmp";
		Highgui.imwrite(pathFile+"/recaleeOptique.jpg", recaleeOptique);
		
		
	}
}
