import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class Main {
	 static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 }
	public static void main(String[] args) {
		
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
		TraitementImage.afficherImage(histImageFinale);
	}
}