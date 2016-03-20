package interfaceProjet.Histogrammes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import main.Main;

import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class AffichageHisto extends JPanel {
	private static final long serialVersionUID = 1L;
	
	AffichageHistoJFrame histoJFrame ;

	Image thermiqueRecal; 
	Image optiqueRecal;
	Image thermiqueMasque;
	Image optiqueMasque;
	
	Image histoThermiqueRecal;
	Image histoOptiqueRecal ;
	Image histoThermiqueMasque ;
	Image histoOptiqueMasque ;
	
	
	public AffichageHisto(final AffichageHistoJFrame histoJFrame) {
		this.histoJFrame = histoJFrame ;
		optiqueRecal = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeOptiqueValid.jpg");
		Mat histoOptiqueRecal = Main.workspace.creerHistogramme(Highgui.imread(Main.PATHFILE+"/recaleeOptiqueValid.jpg"), 1, 255);
		Imgproc.resize(histoOptiqueRecal, histoOptiqueRecal,Main.workspace.optRecal.size());
		Highgui.imwrite(Main.PATHFILE+"/histogrammeOptRecal.jpg", histoOptiqueRecal);
		this.histoOptiqueRecal = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/histogrammeOptRecal.jpg");
		
		thermiqueRecal = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/recaleeThermiqueValid.jpg");
		Mat histoThermiqueRecal = Main.workspace.creerHistogramme(Highgui.imread(Main.PATHFILE+"/recaleeThermiqueValid.jpg"), 1, 255);
		Imgproc.resize(histoThermiqueRecal, histoThermiqueRecal,Main.workspace.optRecal.size());
		Highgui.imwrite(Main.PATHFILE+"/histogrammeThRecal.jpg", histoThermiqueRecal);
		this.histoThermiqueRecal = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/histogrammeThRecal.jpg");
		
		optiqueMasque = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/MasqueOptique.jpg");
		Mat histoOptiqueMasque = Main.workspace.creerHistogramme(Highgui.imread(Main.PATHFILE+"/MasqueOptique.jpg"), 1, 255);
		Imgproc.resize(histoOptiqueMasque, histoOptiqueMasque,Main.workspace.optRecal.size());
		Highgui.imwrite(Main.PATHFILE+"/histogrammeOptMask.jpg", histoOptiqueMasque);
		this.histoOptiqueMasque = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/histogrammeOptMask.jpg");
		
		thermiqueMasque = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/MasqueThermique.jpg");
		Mat histoThermiqueMasque = Main.workspace.creerHistogramme(Highgui.imread(Main.PATHFILE+"/MasqueThermique.jpg"), 1, 255);
		Imgproc.resize(histoThermiqueMasque, histoThermiqueMasque,Main.workspace.optRecal.size());
		Highgui.imwrite(Main.PATHFILE+"/histogrammeThMask.jpg", histoThermiqueMasque);
		this.histoThermiqueMasque = Toolkit.getDefaultToolkit().createImage(Main.PATHFILE+"/histogrammeThMask.jpg");
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(optiqueRecal, 
				20, 
				80, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(histoOptiqueRecal, 
				20 + optiqueRecal.getWidth(this)/3 + 10 , 
				80, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		

		g.drawImage(thermiqueRecal, 
				20, 
				300, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(histoThermiqueRecal, 
				20 + optiqueRecal.getWidth(this)/3 + 10 , 
				300, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(optiqueMasque, 
				20, 
				520, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(histoOptiqueMasque, 
				20 + optiqueRecal.getWidth(this)/3 + 10 , 
				520, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(thermiqueMasque, 
				20, 
				740, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
		
		g.drawImage(histoThermiqueMasque, 
				20 + optiqueRecal.getWidth(this)/3 + 10 , 
				740, 
				optiqueRecal.getWidth(this)/3, 
				optiqueRecal.getHeight(this)/3, 
				this);
	}

}
