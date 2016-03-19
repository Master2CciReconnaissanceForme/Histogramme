package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import modelProjet.Photos;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class ConnectInterfaceWorkspace {

	public Photos photos;
	public Mat sourceOpt;
	public Mat sourceTh;
	
	public Mat sourceOptGray;
	public Mat sourceThGray;
	
	public static Mat masque = null;
	
	public ConnectInterfaceWorkspace(Vector<Photos> workspace) {
		photos = workspace.get(0);
		sourceOpt = Highgui.imread(photos.optOrigine);
		sourceTh = Highgui.imread(photos.thOrigine);
		
		sourceOptGray = Highgui.imread(photos.optOrigine, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
		sourceThGray = Highgui.imread(photos.thOrigine, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
	}

	public static void creerMask(Mat image, int seuilMini, int seuilMaxi, int canalCouleur){
		List<Mat> mat3Color = new ArrayList<Mat>(3);
		Mat imageMult = new Mat();
		Core.multiply (image, new Scalar (2.5, 2.5, 2.5), imageMult);
		Core.split(imageMult, mat3Color);
		imageMult = mat3Color.get(canalCouleur);
		
		Imgproc.threshold(imageMult, imageMult, seuilMini, seuilMaxi, Imgproc.THRESH_BINARY_INV);

		masque = imageMult;	
	}
	
	public static Mat superposerMask(Mat image, Mat mask, boolean remplissage){
		Mat sourceTemp = image.clone();
		
		if(remplissage)
			Core.subtract(sourceTemp, new Scalar(255,255,255), sourceTemp, mask);
		else
			Core.add(sourceTemp, new Scalar(255,255,255), sourceTemp, mask);

		return sourceTemp;
	}
	
	public static Mat creerHistogramme(Mat image, int rangeMin, int rangeMax) {
		java.util.List<Mat> matList = new LinkedList<Mat>();
		matList.add(image);
		
		Mat histogram = new Mat();
		MatOfFloat ranges = new MatOfFloat(rangeMin, rangeMax);
		MatOfInt histSize = new MatOfInt(255);
		
		Imgproc.calcHist(matList, new MatOfInt(0), new Mat(), histogram,
				histSize, ranges);

		Mat histImage = Mat.zeros(100, (int) histSize.get(0, 0)[0],
				CvType.CV_8UC1);

		Core.normalize(histogram, histogram, 1, histImage.rows(),
				Core.NORM_MINMAX, -1, new Mat());

		for (int i = 0; i < (int) histSize.get(0, 0)[0]; i++) {
			Core.line(
					histImage,
					new org.opencv.core.Point(i, histImage.rows()),
					new org.opencv.core.Point(i, histImage.rows()
							- Math.round(histogram.get(i, 0)[0])), new Scalar(
							255), 1, 8, 0);
		}
		return histImage;
	}
	
	public static void afficherImage(Mat image) {
		
		MatOfByte matOfByte = new MatOfByte();
		Highgui.imencode(".png", image, matOfByte);
		byte[] byteArray = matOfByte.toArray();
		BufferedImage bufImage = null;

		try {
			InputStream in = new ByteArrayInputStream(byteArray);
			bufImage = ImageIO.read(in);
			JFrame frame = new JFrame();
			frame.getContentPane().add(new JLabel(new ImageIcon(bufImage)));
			frame.pack();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
