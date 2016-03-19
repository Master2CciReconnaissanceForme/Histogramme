package interfaceProjet.Utils;

import java.io.File;

import javax.swing.JFileChooser;

public class UtilFiles{
	public static String ParcourirFiles() {
			JFileChooser dialogue = new JFileChooser(new File("/"));
			File fichier = null;
			
			if (dialogue.showOpenDialog(null)==
					JFileChooser.APPROVE_OPTION) { fichier = dialogue.getSelectedFile(); }
			return fichier.toString();
	}
}