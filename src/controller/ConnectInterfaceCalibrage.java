package controller;

import java.io.File;

import org.opencv.core.Mat;

import modelProjet.RecalageFixe;
import modelProjet.RecalageTemoin;

public abstract class ConnectInterfaceCalibrage {

	public static Mat calibrageManuel(String optOrigine, String thOrigine){
		new RecalageTemoin(optOrigine, thOrigine);
		return RecalageTemoin.recaleeOptique;
	}
	
	public static Mat calibrageAuto(String optOrigine, String thOrigine){
		new RecalageFixe(optOrigine, thOrigine);
		return RecalageFixe.recaleeOptique;
	}
	
}
