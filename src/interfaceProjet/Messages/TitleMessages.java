package interfaceProjet.Messages;

public abstract class TitleMessages {
	
	/* ValidationJPanel */
	public final static String SAVERECALAGE = "<html><center>"
			+ "<br>Si vous êtes satisfait de ce recalage, cliquez sur \"Valider\". "
			+ "<br>Sinon, cliquez sur \"Recalibrer\" afin de procéder à un calibrage manuel adapté à vos images."
			+ "</center></html>" ;

	/* NextJDialog pour proposition de masque */
	public final static String CREATEMASQUE = "<html> <center> "
			+ "<br>Vos images recalées ont bien été prises en compte. "
			+ "<br>Souhaitez-vous poursuivre le traitement et créer le "
			+ "<br>masque global associé à ce couple d'images ?"
			+ "</center></html>" ;
	
	/* MasqueJPanel */
	public final static String SAVEMASQUE = "<html><center>"
			+ "<br>Si vous êtes satisfait de ce masque, cliquez sur \"Sauvegarder\". "
			+ "<br>Sinon, cliquez sur \"Annuler\". "
			+ "</center></html>" ;
	
	/* NextJDialog pour proposition d'histogramme */
	public final static String CREATEHISTOGRAMME = "<html> <center> "
			+ "<br>Le masque créé a bien été pris en compte et sauvegardé. "
			+ "<br>Souhaitez-vous poursuivre le traitement et calculer "
			+ "<br>l'histogramme de températures de votre plante ?" 
			+ "</center></html>" ;

	/* HistoJPanel */
	public final static String ENDHISTOGRAMME = "<html><center>"
			+ "<br>Voici l'histogramme de températures associé à la plante étudiée."
			+ "<br>Pour quitter le projet, cliquez sur \"Terminer\"."
			+ "</center></html>" ;	
}
