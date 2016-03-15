package utilBDDProjet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Connect {
	
    final static String url = "jdbc:mysql://localhost/plantesanalyse";
    final static String user = "root";
    final static String passwd = "root";
	
	protected static Connection conn=null;

	public Connect(){
			try{
				Class.forName("com.mysql.jdbc.Driver");
			
			    System.out.println("Driver O.K.");
			
			    conn = DriverManager.getConnection(url, user, passwd);
			
			    System.out.println("Connexion effective !");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
	}
	
	public static void main(String args[]){

		new Requetes();
		if(Requetes.nouvellePlante("cana","bis"))
			System.out.println("ca marche");
		int temp = Requetes.dernierIdPlante();
		System.out.println(temp);
		Date date = new Date();

		//Requetes.savePhotoOrigine(temp, "file1.jpg", "file2.png", date);
		
	}
}
