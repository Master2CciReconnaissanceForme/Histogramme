package modelProjet;

import java.util.Date;

public class Masques {

	int idMasque;
	int idPhoto;
	String masque;
	String typeMasque;
	boolean valide;
	
	public Masques(int idMasque, int idPhoto, String masque, String typeMasque, boolean valide) {
		this.idMasque = idMasque;
		this.idPhoto = idPhoto;
		this.masque = masque;
		this.typeMasque = typeMasque;
		this.valide = valide;
	}

}
