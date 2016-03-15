import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencv.core.Core;

import utilBDDProjet.Requetes;

public class Main {
	 static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 }
	public static void main(String[] args) {
		/*
		Mat source = Highgui.imread("C:/Thermique.jpg");
		Mat sourceGray = Highgui.imread("C:/Thermique.jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);	

		Mat mask = TraitementImage.creerMask(source, 254, 255, 2);

		Mat finale = TraitementImage.superposerMask(source, mask, true);
		
		Mat histImageSource = TraitementImage.creerHistogramme(source, 0, 256);
		Mat histImageSourceGray = TraitementImage.creerHistogramme(sourceGray, 0, 256);
		Mat histImageFinale = TraitementImage.creerHistogramme(finale, 1, 256);

		TraitementImage.afficherImage(source);
		TraitementImage.afficherImage(histImageSource);

		TraitementImage.afficherImage(sourceGray);
		TraitementImage.afficherImage(histImageSourceGray);
		
		TraitementImage.afficherImage(finale);
		TraitementImage.afficherImage(histImageFinale);*/
		
		String selectedDate = "2016-03-15";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = simpleDateFormat.parse(selectedDate);
			System.out.println(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Requetes requetes = new Requetes();
		System.out.println(requetes.listePhotos(date));
		}
}