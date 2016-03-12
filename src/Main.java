import java.io.File;
import java.net.URISyntaxException;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class Main {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(final String[] args) {

		String pathFile = null;
		try {
			pathFile = new File(Main.class.getResource("Thermique.jpg").toURI()).getAbsolutePath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Mat source = Highgui.imread(pathFile);
		Mat sourceGray = Highgui.imread(pathFile, Highgui.CV_LOAD_IMAGE_GRAYSCALE);

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
		TraitementImage.afficherImage(histImageFinale);
	}
}