package interfaceProjet.Calibrage;

import java.awt.AlphaComposite;
import java.awt.Choice;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class CalibrageJPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CalibrageJFrame imJFrame ;
	
	Image imOptique ;
	Image imThermique ;	
	int jOptique ;
	int iOptique ;
	double facteur ;
	int dec ;
	
	public CalibrageJPanel(final CalibrageJFrame imJFrame) {
		this.imJFrame = imJFrame ;
		Highgui.imwrite(Main.PATHFILE+"/imOptique.jpg", imJFrame.recalageTemoin.sourceOptique);

		imOptique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/imOptique.jpg");
		
		Highgui.imwrite(Main.PATHFILE+"/imThermique.jpg", imJFrame.recalageTemoin.sourceThermique);
		imThermique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/imThermique.jpg");
		
		jOptique = 0 ;
		iOptique = 0 ;
		facteur = 1 ;
		dec = 1 ; 
		 
		JButton haut = new JButton("haut");
		JButton gauche = new JButton("gauche");
		JButton droite = new JButton("droite");
		JButton bas = new JButton("bas");
		
		
		final Choice decalage = new Choice();
		 
		for (int d = 0 ; d < 500 ; d++)
			decalage.addItem(""+d);
		decalage.select(1);
		
		add(decalage);
		
		 decalage.addItemListener(new ItemListener() {
			 
			 @Override
			 public void itemStateChanged(ItemEvent e) {
				  dec = decalage.getSelectedIndex() ;	
			 }
		 });
			
		 JButton in = new JButton("+");
		 JButton out = new JButton("-");
		 
		 //en Standby mais si on peut l'ins�rer, on l'ins�re et il  faudra ins�rer AUSSI dans les fonction de recalage une fois que l'angle est r�cup�r� ici
		 JButton pivoter = new JButton("pivoter");

		 
		 in.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				facteur += 0.01 ;
				Mat mat3 = new Mat( (int)(imJFrame.recalageTemoin.sourceOptique.rows()* facteur),  (int)(imJFrame.recalageTemoin.sourceOptique.cols()* facteur), CvType.CV_8UC1);
				Size size = new Size((int)(imJFrame.recalageTemoin.sourceOptique.cols()* facteur) , (int)(imJFrame.recalageTemoin.sourceOptique.rows()* facteur));
				Imgproc.resize(imJFrame.recalageTemoin.sourceOptique, mat3, size);
				Highgui.imwrite(Main.PATHFILE+"/imOptique.jpg", mat3);
				imOptique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/imOptique.jpg");
				repaint();
			}
		});
		 
		 out.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					facteur -= 0.01 ;
					Mat mat3 = new Mat( (int)(imJFrame.recalageTemoin.sourceOptique.rows()* facteur),  (int)(imJFrame.recalageTemoin.sourceOptique.cols()* facteur), CvType.CV_8UC1);
					Size size = new Size((int)(imJFrame.recalageTemoin.sourceOptique.cols()* facteur) , (int)(imJFrame.recalageTemoin.sourceOptique.rows()* facteur));
					Imgproc.resize(imJFrame.recalageTemoin.sourceOptique, mat3, size);
					Highgui.imwrite(Main.PATHFILE+"/imOptique.jpg", mat3);
					imOptique = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/imOptique.jpg");
					repaint();
				}
			});
		 
		 gauche.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (jOptique > 0)
					jOptique -= dec ;
				repaint();
			}
		 });
		 
		 droite.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (jOptique + imThermique.getHeight(null) < imJFrame.recalageTemoin.sourceOptique.cols())
						jOptique += dec ;
					repaint();
				}
			 });

		 bas.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (iOptique + imThermique.getWidth(null) < imJFrame.recalageTemoin.sourceOptique.rows())
						iOptique += dec ;
					repaint();
				}
			 });
		 
		 haut.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (iOptique > 0)
						iOptique -= dec ;
					repaint();
				}
			 });
		 
		 
		 
		 JButton end = new JButton("Termin�");
		 end.addActionListener(new ActionListener() {

			 @Override
			 public void actionPerformed(ActionEvent arg0) {
				 System.out.println("facteur = "+facteur+" , j = "+jOptique+" , i = "+iOptique);
				 try {
					 FileWriter file = new FileWriter(Main.PATHPARAMS);
					 file.write(facteur+"\n"+(iOptique*3)+"\n"+(jOptique*3));
					 file.flush();
					 file.close();
				 } catch (IOException e) {
					 e.printStackTrace();
				 }
				 	imJFrame.recalageTemoin.recalageFinalOptique(facteur, iOptique*3,jOptique*3); 
				 }
			 });
		 
		 add(haut);
		 add(bas);		 
		 add(gauche);		 
		 add(droite);
		 add(in);
		 add(out);
		 add(end);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//Mise en transparence des images
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

		g2d.drawImage(imOptique, 0, 0, imOptique.getWidth(this)/3, imOptique.getHeight(this)/3, this);
		g2d.drawImage(imThermique, jOptique, iOptique, imThermique.getWidth(this)/3, imThermique.getHeight(this)/3, this);

	}
}
