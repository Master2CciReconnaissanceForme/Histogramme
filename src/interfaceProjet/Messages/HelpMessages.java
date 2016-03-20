package interfaceProjet.Messages;

public abstract class HelpMessages {

	protected final static String NEWPROJECT = "<html>"
			+ "<br>"
			+ "<br>Vous devez donner un nom à votre projet et télécharger"
			+ "<br>le couple d'images optique et thermique à traiter."
			+ "<br>"
			+ "<br>Les images seront recalées et vous aurez la possibilité"
			+ "<br>de les sauver pour poursuivre le traitement pour créer le"
			+ "<br>masque associé et calculer l'histogramme de températures"
			+ "<br>de la plante étudiée."
			+ "<br>"
			+ "<br><i>Les champs précédés d'une * sont obligatoires."
			+ "<br>"
			+ "</html>"; 
	
	//Faire un bouton INFOS au lieu de ANNULER, pensez à mettre "terminer" au lieu de "sauvegarder"
	protected final static String HISTOGRAMME = "<html>"
			+ "<br>"
			+ "<br>L'histogramme représente la somme des pixels de même valeur,"
			+ "<br>pour des valeurs comprises entre 0 et 255 (niveaux de gris)."
			+ "<br>Il est calculé à partir de l'image thermique recouverte du "
			+ "<br>masque précédemment créé dans le but d'étudier les informations "
			+ "<br>en température de la plante étudiée."
			+ "<br>"
			+ "</html>"; 
	
	/* MasqueJPanel bouton Infos ou Aide à ajouter, modifier le bouton "annuler" par "terminer" pour être cohérent */
	protected final static String MASQUE = "<html>"
			+ "<br>"
			+ "<br>Le masque est une image binaire qui a pour but d’isoler une plante de son contexte."
			+ "<br>Si ce masque, superposé aux images optique et thermique, permet de visualiser la plante"
			+ "<br>entièrement et isolée du fond, vous pouvez enregistrer le masque en cliquant sur \"Sauvegarder\""
			+ "<br>et poursuivre le traitement si vous le souhaitez. Sinon, cliquez sur \"Terminer\"."
			+ "<br>"
			+ "</html>"; 
	
	/* ValidationJPanel : si ajout bouton infos ou aide pour expliquer ce qu'on a */
	protected final static String RECALAGE = "<html>"
		+ "<br>"
		+ "<br>Pour poursuivre le traitement, les deux images fournies doivent être superposables."
		+ "<br>Le recalage présenté a été effectué à partir des données enregistrées lors de la"
		+ "<br>dernière utilisation du logiciel. Si les deux images ne sont pas correstement recalées,"
		+ "<br>veuillez cliquer sur \"Recalibrer\" afin de recaler vos images manuellement. Sinon,"
		+ "<br>cliquez sur \"Valider\". Vous pourrez poursuivre le traitement si vous le souhaiter."
		+ "</html>"; 
}
