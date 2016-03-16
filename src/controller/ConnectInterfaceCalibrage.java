package controller;

import java.io.File;

import modelProjet.RecalageFixe;
import modelProjet.RecalageTemoin;

public abstract class ConnectInterfaceCalibrage {

	public static void calibrageManuel(String optOrigine, String thOrigine){
		new RecalageTemoin(optOrigine, thOrigine);
	}
	
	public static void calibrageAuto(String optOrigine, String thOrigine){
		new RecalageFixe(optOrigine, thOrigine);
	}
	
}
