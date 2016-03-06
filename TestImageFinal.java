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

/*****************************************************************************************************/
/** Le code est identique au code initial, j'ai ajouté une bloc d'instructions pour obtenir l'histo **/
/*****************************************************************************************************/

public class TestImageFinal {
	 static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 }
	public static void main(String[] args) {
		Mat source = Highgui.imread("C:/TestProjet.png");
		Mat dest = new Mat (source.size(), CvType.CV_8UC1);
		
		Mat sourceGray = new Mat (source.size(), CvType.CV_8UC1);
		Mat sourceBlur = new Mat(sourceGray.size(),CvType.CV_8UC1);
		Mat cannyFinal = new Mat(sourceBlur.size(),CvType.CV_8UC1);
		
		Imgproc.cvtColor(source, sourceGray, Imgproc.COLOR_BGR2GRAY);	
		
		/** Debut du code Histogramme **/	
		java.util.List<Mat> matList = new LinkedList<Mat>();
		matList.add(sourceGray);
		Mat histogram = new Mat();
		MatOfFloat ranges=new MatOfFloat(0,256);
		MatOfInt histSize = new MatOfInt(255);
		Imgproc.calcHist(matList, new MatOfInt(0), new Mat(), histogram , histSize , ranges);
		
		Mat histImage = Mat.zeros( 100, (int)histSize.get(0, 0)[0], CvType.CV_8UC1);
		
		Core.normalize(histogram, histogram, 1, histImage.rows() , Core.NORM_MINMAX, -1, new Mat() );   
		
		for( int i = 0; i < (int)histSize.get(0, 0)[0]; i++ )
		{                   
		       Core.line(histImage, new org.opencv.core.Point( i, histImage.rows() ), new org.opencv.core.Point( i, histImage.rows()-Math.round( histogram.get(i,0)[0] )), new Scalar( 255), 1, 8, 0 );
		}
		/** Fin du code Histogramme **/
		
		MatOfByte matOfByte = new MatOfByte();
		Highgui.imencode(".png", histImage, matOfByte);
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
	       
		Imgproc.GaussianBlur (sourceGray, sourceBlur, new Size(9,9), 0);
		Imgproc.Canny(sourceBlur, cannyFinal, 100, 200);

		Highgui.imwrite("C:/Gary_Movie.png",sourceGray);
		//Imgproc.resize(sourceGray, sourceGray, new Size(800, 480));
		MatOfByte matOfByte1 = new MatOfByte();
		Highgui.imencode(".png", sourceGray, matOfByte1);
		byte[] byteArray1 = matOfByte1.toArray();
		BufferedImage bufImage1 = null;
	        
	        try {
	     	   
	            InputStream in = new ByteArrayInputStream(byteArray1);
	            bufImage1 = ImageIO.read(in);
	            JFrame frame = new JFrame();
	            frame.getContentPane().add(new JLabel(new ImageIcon(bufImage1)));
	            frame.pack();
	            frame.setVisible(true);
	      	} catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
