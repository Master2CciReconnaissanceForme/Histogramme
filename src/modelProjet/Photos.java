package modelProjet;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;



public class Photos {

	public int idPhoto;
	public int idPlante;
	public Date datePhoto;
	public String optOrigine;
	public String thOrigine;
	public String optRecal;
	public String threcal;
	public float longitude;
	public float latitude;
	
	static Mat imageOptOrigine;
	static Mat imageThOrigine;
	static Mat imageOptRecal;
	static Mat imageThrecal;
	
	public Photos(int idPhoto, int idPlante, Date datePhoto, String optOrigine, String thOrigine, String optRecal, String threcal, float longitude, float latitude) {
		this.idPhoto = idPhoto;
		this.idPlante = idPlante;
		this.datePhoto = datePhoto;
		this.optOrigine = optOrigine;
		this.thOrigine = thOrigine;
		this.optRecal = optRecal;
		this.threcal = threcal;
		this.longitude = longitude;
		this.latitude = latitude;
		
		imageOptOrigine = Highgui.imread(optOrigine);
		imageThOrigine = Highgui.imread(thOrigine);
		//imageOptRecal = Highgui.imread(optRecal);
		//imageThrecal = Highgui.imread(threcal);
	}
	
	@Override
	public String toString() {
		return idPhoto+" "+idPlante+" "+datePhoto+" "+optOrigine+" "+thOrigine+" "+optRecal+" "+threcal+" "+longitude+" "+latitude;
	}
}
