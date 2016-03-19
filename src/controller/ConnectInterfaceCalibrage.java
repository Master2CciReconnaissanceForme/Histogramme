package controller;

import interfaceProjet.Calibrage.ValidationJFrame;
import modelProjet.RecalageFixe;
import modelProjet.RecalageTemoin;

import org.opencv.core.Mat;

public abstract class ConnectInterfaceCalibrage {

	public static Mat calibrageManuel(String optOrigine, String thOrigine){
		new RecalageTemoin(optOrigine, thOrigine);
		return RecalageTemoin.recaleeOptique;
	}
	
	public static Mat calibrageAuto(String optOrigine, String thOrigine){
		ValidationJFrame validationFrame = new ValidationJFrame(new RecalageFixe(optOrigine, thOrigine), null);
		return RecalageFixe.recaleeOptique;
	}
	
}
