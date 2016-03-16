package modelProjet;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Size;
import org.opencv.features2d.DMatch;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class RecalageClassique {
	public Mat sourceContenant  ; 
	public Mat sourceContenue 	; 
	public Mat recaleeContenant	;

	public RecalageClassique(String cheminContenant, String cheminContenue) {
		sourceContenant = Highgui.imread(cheminContenant) ;
		sourceContenue  = Highgui.imread(cheminContenue) ;
		recalage();
	}
	

	public void recalage() {
		
		Mat grayMatContenant = toGrayFromBGR(sourceContenant);
		MatOfKeyPoint keyPointsContenant = toKeyPoints(grayMatContenant);
		Mat descriptorMatContenant = computeDescriptor(grayMatContenant, keyPointsContenant);
		
		Mat grayMatContenue = toGrayFromBGR(sourceContenue);
		MatOfKeyPoint keyPointsContenue = toKeyPoints(grayMatContenue);
		Mat descriptorMatContenue = computeDescriptor(grayMatContenue, keyPointsContenue);
		
		MatOfDMatch matches = findMatches(grayMatContenue, keyPointsContenue, grayMatContenant, keyPointsContenant, descriptorMatContenue, descriptorMatContenant) ;
		int[][] goodMatches = indexOfGoodKeyPointsMatches(matches);
		
		double mediane = findShrinkageFactor(goodMatches, keyPointsContenant, keyPointsContenue);
		
		recaleeContenant = recalageSourceContenant(sourceContenant, sourceContenue, mediane);

	}
	
	
	
	/* ********************************************************************************************/
	//Traitement de CHAQUE IMAGE en s�par� jusqu'� description des points caract�ristiques 
	//mais besoin de conserver � part chaque traitement pour la suite

	//1
	public Mat toGrayFromBGR(Mat colorMat) {
		Mat grayMat = new Mat(colorMat.size(), CvType.CV_8UC1);
		Imgproc.cvtColor(colorMat, grayMat, Imgproc.COLOR_BGR2GRAY);
		return grayMat ;
	}
	
	//2
	public MatOfKeyPoint toKeyPoints(Mat grayMat) {
		FeatureDetector fd = FeatureDetector.create(FeatureDetector.SURF);
		MatOfKeyPoint keyPoints = new MatOfKeyPoint();
		fd.detect(grayMat, keyPoints);
		Mat drawnKeyPoints = toDrawKeyPoints(grayMat, keyPoints);
		
		return keyPoints ;
	}
	
	//2 Bis Optionnel (visualisation surtout)
	public Mat toDrawKeyPoints(Mat grayMat, MatOfKeyPoint keyPoints) {
		Mat drawnKeyPoints = new Mat() ;
		Features2d.drawKeypoints(grayMat, keyPoints, drawnKeyPoints) ;
		//Enregistrement physique
		return drawnKeyPoints ;
	}
	
	//3 
	public Mat computeDescriptor(Mat grayMat, MatOfKeyPoint keyPoints) {
		DescriptorExtractor de = DescriptorExtractor.create(DescriptorExtractor.SURF);
		Mat descriptorMat = new Mat();
		de.compute(grayMat, keyPoints, descriptorMat);
		return descriptorMat ;
	}
	
	/* **********************************************************/
	// Mise en correspondance des deux analyses initiales 
	
	//1
	public MatOfDMatch findMatches(Mat grayMatContenue, MatOfKeyPoint keyPointsContenue , Mat grayMatContenant, MatOfKeyPoint keyPointsContenant, Mat descriptorMatContenue, Mat descriptorMatContenant) {
		DescriptorMatcher descriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
		MatOfDMatch matches = new MatOfDMatch();
		descriptorMatcher.match(descriptorMatContenue,descriptorMatContenant, matches);
		drawMatches(grayMatContenue, keyPointsContenue, grayMatContenant, keyPointsContenant, matches);
		return matches ;
	}
	
	//1 Bis Optionnel (visualisation des matches et justification de l'affinage)
	public Mat drawMatches(Mat grayMatContenue, MatOfKeyPoint keyPointsContenue , Mat grayMatContenant, MatOfKeyPoint keyPointsContenant, MatOfDMatch matches) {
		Mat grayWithMatches = new Mat();
		Features2d.drawMatches(grayMatContenue, keyPointsContenue, grayMatContenant, keyPointsContenant, matches, grayWithMatches); 
		return grayWithMatches ;
	}

	
	/* ****************************************************************************************** */
	// On affine le r�sultat pour enlever les points foireux qu'on peut lever nous-m�mes 
		// soit les points qui sont plusieurs fois dans la matrice

	public int[][] indexOfGoodKeyPointsMatches (MatOfDMatch matches) {
		int[][] tabMatches = indexOfkeyPointsMatches(matches);
		deleteBadMatches (tabMatches);
		return copyGoodMatches(tabMatches); 
	}	
	
	//1 : r�cup des matches de la liste pour les mettre sdans un tableau tampon
	public static int [][] indexOfkeyPointsMatches(MatOfDMatch matches){
		List <DMatch> listMatches = matches.toList() ;
		Iterator<DMatch> itrMatches = listMatches.iterator(); 
		int [][] tabMatches = new int[listMatches.size()][2];
		copyMatchesInTab(listMatches, itrMatches, tabMatches); //Appel � fonction
		return tabMatches;
	}
	
	//1 Bis
	public static void copyMatchesInTab(List <DMatch> listMatches,Iterator<DMatch> itrMatches, int [][] tabMatches) {
		int cpt = 0 ;
		
		while (itrMatches.hasNext()) {
			DMatch match = itrMatches.next() ;
			tabMatches[cpt][0] = match.queryIdx ;
			tabMatches[cpt][1] = match.trainIdx ;
			cpt++;
		}
	}
	
	//2 : on veut supprimer les mauvais points donc on modifie la valeur des index foireux (en mettant -1 donc rep�rables)
	public static void deleteBadMatches (int[][] tabMatches){
		
		for (int i = 0 ; i < tabMatches.length ; i++) {
			boolean isChanged = false ;
			
			if (tabMatches[i][1] != -1) {
				
				for (int j = i+1 ; j < tabMatches.length ; j++)
					if (tabMatches[i][1] == tabMatches[j][1]) {
						tabMatches[j][1] = -1 ;  
						isChanged = true ;
					}
				
				if (isChanged)
					tabMatches[i][1] = -1 ;
			}
		}
	}
		
	//3 : on cr�e un nouveau tableau avec la bonne dimension pour accueillir tous les points conserv�s
	public static int[][] copyGoodMatches(int[][] tabMatches) {

		int nbGoodMatches = 0 ;

		for (int i = 0 ; i < tabMatches.length ; i++) {
			if (tabMatches[i][1] != -1) {
				nbGoodMatches++ ;
			}
		}
			
		int [][] goodMatches = new int[nbGoodMatches][2];
		int cpt = 0 ;
		
		for (int j = 0 ; j < tabMatches.length ; j++) {

			if (tabMatches[j][1] != -1) {
				goodMatches[cpt][0] = tabMatches[j][0];
				goodMatches[cpt][1] = tabMatches[j][1];
				cpt++; 
			}
		}
		return goodMatches ;
	}		

	
	/* ****************************************************************************************** */
	//Avec le tableau de matches, on va s'occuper de chaque image s�par�ment pour calculer les distances entre les points
	
	public double findShrinkageFactor(int[][] goodMatches, MatOfKeyPoint keyPointsContenant, MatOfKeyPoint keyPointsContenue) {
		double[][] pointsContenant = coordinatesKeyPoints(goodMatches, keyPointsContenant, 1) ;
		double[][] pointsContenue = coordinatesKeyPoints(goodMatches, keyPointsContenue, 0) ;
		
		double[] distancesContenant = distances(pointsContenant);
		double[] distancesContenue = distances(pointsContenue);
		
		double[] rapportDistances = rapportsDistances (distancesContenue, distancesContenant);
		double mediane = medianeRapportsDistances(rapportDistances) ;
		
		return mediane ;
	}
	
	
	
	//1 : on r�cup�re les coordonn�es des points de la matrice concern�e (sourcePoint)
	public double[][] coordinatesKeyPoints(int[][] goodMatches, MatOfKeyPoint keyPoints, int sourcePoint) {
		
		double[][] points = new double[goodMatches.length][2];		
		List <KeyPoint> listGoodKeyPoints = keyPoints.toList() ;
		for(int i = 0 ; i < goodMatches.length ; i++){
			points[i][0] = listGoodKeyPoints.get(goodMatches[i][sourcePoint]).pt.x;
			points[i][1] = listGoodKeyPoints.get(goodMatches[i][sourcePoint]).pt.y;
		}
		return points ;
	}
	
	//2 On calcule nos distances deux � deux entre points i et i+1 
	public double[] distances(double[][]points) {
		double [] distances = new double [points.length-1] ; 
		for (int i = 0 ; i < distances.length ; i++)
			distances[i] = Math.sqrt( ( points[i+1][0] - points[i][0] ) * ( points[i+1][0] - points[i][0] ) 
									  + ( points[i+1][1] - points[i][1] ) * ( points[i+1][1] - points[i][1] ));
		return distances ;
	}
	
	
	//3 : calcul des rapports de distances entre les deux images
	public double[]rapportsDistances (double[] distancesContenue, double[] distancesContenant) {
		double [] rapportDist = new double [distancesContenue.length] ; 
		for (int i = 0 ; i < rapportDist.length ; i++) {
			rapportDist[i] = distancesContenue[i]/distancesContenant[i] ;
		}
		return rapportDist ;
	} 
	
	//4 : on tri le tableau de rapport pour trouver la valeur m�diane qui sera le facteur de r�tr�cissement
	public double medianeRapportsDistances(double[] rapportDist) {
		Arrays.sort(rapportDist);
		double mediane = rapportDist[rapportDist.length/2];
		System.out.println(mediane);
		return mediane ;
	}
	
	
	
	/* ************************************ */
	//On fait le recalage : resize + rognage
	
	public Mat recalageSourceContenant(Mat sourceContenant, Mat sourceContenue, double facteur) {
		Mat sourceContenantRecalee = resizeSourceContenantRecalee(sourceContenant, facteur);
		Mat sourceContenantRognee = rognageSourceContenant(sourceContenantRecalee, sourceContenue);	
		return sourceContenantRognee ;
	}
	
	//1
	public Mat resizeSourceContenantRecalee(Mat sourceContenant, double facteur) {
		Mat sourceContenantRecalee = new Mat( (int)(sourceContenant.rows()* facteur),  (int)(sourceContenant.cols()* facteur), CvType.CV_8UC1);
		Size size = new Size((int)(sourceContenant.cols()* facteur) , (int)(sourceContenant.rows()* facteur));
		Imgproc.resize(sourceContenant, sourceContenantRecalee, size);
		Highgui.imwrite("D:/Recalage/recaleeContenant.jpg", sourceContenantRecalee);

		return sourceContenantRecalee ;
	}
	
	//2
	public Mat rognageSourceContenant(Mat sourceContenantRecalee, Mat sourceContenue)  {
		Mat sourceContenantRognee = new Mat(sourceContenue.size(), CvType.CV_8UC1) ;
		int i = 0, j = 0 ;
		boolean same = false ;
		
		for (i = 0 ; i < sourceContenantRecalee.rows() - sourceContenue.rows() ; i++) {
			for (j = 0 ; j < sourceContenantRecalee.cols() - sourceContenue.cols() ; j++) {

				if ( Arrays.equals(sourceContenantRecalee.get(i, j), sourceContenue.get(0, 0))) {
					same = true ;
					System.out.println("true");
					for (int i2 = 0 ; i2 < sourceContenue.rows() ; i2++) {
						for (int j2 = 0 ; j2 < sourceContenue.cols() ; j2++) {
							if ( !Arrays.equals(sourceContenantRecalee.get(i+i2, j+j2), sourceContenue.get(i2, j2)) ) 
								same = false ;

							if (same == false)
								j2 = sourceContenue.cols() ;
						}
						if (same == false)
							i2 = sourceContenue.rows() ;
					}
				}
				if (same == true)
					break ;
			}

		}
		System.out.println("i = "+i+" et j = "+j);
		for (int i3 = 0 ; i3 < sourceContenantRognee.rows() ; i3++)
			for (int j3 = 0 ; j3 < sourceContenantRognee.cols() ; j3++)		
				sourceContenantRognee.put(i3, j3, sourceContenantRecalee.get(i3 + i, j3 + j));
		Highgui.imwrite("D:/Recalage/rogneeContenant.jpg", sourceContenantRognee);

		return sourceContenantRognee;
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
