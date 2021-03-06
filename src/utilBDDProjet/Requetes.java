package utilBDDProjet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import modelProjet.Masques;
import modelProjet.Photos;

public class Requetes extends Connect {

	public static String requete;
	public static Statement state;
	public static PreparedStatement prstate;
	public static ResultSet result;
	public static ResultSetMetaData resultMeta;

	public Requetes() {

	}

	public static boolean nouvellePlante(String nomsci, String nomcom) {
		boolean res=false;
		try {
			requete = "INSERT INTO plantes (nomsci,nomcom) VALUES (?,?);";
			prstate = conn.prepareStatement(requete);
			
			prstate.setString(1, nomsci);
			prstate.setString(2, nomcom);
			
			//System.out.println(prstate.toString());
			
			prstate.executeUpdate();
			
			res=true;

		} catch (SQLException e) {
			e.printStackTrace();
			res=false;
		} finally {
				try{
         			if(prstate!=null)
            		prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
   		}	
		return res;//Le cas d'erreur est le boolean
	}
	
	public static int dernierIdPlante(){
		int res = 0;
		
		try {
			requete = "SELECT MAX(idplante) FROM plantes;";
			state = conn.createStatement();
			result = state.executeQuery(requete);
			
			result.next();
			res = result.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try{
         			if(result!=null)
            		result.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
				try{
         			if(state!=null)
            		state.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
   		}		
		return res; //le cas d'erreur est le 0
	}
	
	public static boolean savePhotoOrigine(int idplante, String opOri, String thOri, Date datephoto){
		boolean res=false;
		try {
			requete = "INSERT INTO photos (idplante,datephoto,optiqueorigine,thermiqueorigine) VALUES (?,?,?,?);";
			prstate = conn.prepareStatement(requete);
			
			prstate.setInt(1, idplante);
			prstate.setObject(2, datephoto);
			prstate.setString(3, opOri);
			prstate.setString(4, thOri);
	
			//System.out.println(prstate.toString());
			
			prstate.executeUpdate();

			res = true;

		} catch (SQLException e) {
			e.printStackTrace();	
			res = false;
		}finally {
				try{
         			if(prstate!=null)
            		prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
   		}
   		return res;	//le boolean est le cas d'erreur
	}
	
	public static boolean saveOpticRecal(Date datephoto, String oprecal){
		boolean res=false;
		try {
			requete = "	UPDATE photos SET optiquerecale= ? WHERE datephoto= ?;";
			prstate = conn.prepareStatement(requete);

			prstate.setString(1, oprecal);
			prstate.setObject(2, datephoto);
	
			//System.out.println(prstate.toString());
			
			prstate.executeUpdate();

			res=true;

		} catch (SQLException e) {
			e.printStackTrace();	
			res=false;
		}finally {
				try{
         			if(prstate!=null)
            		prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
      	return res;//le boolean est le cas d'erreur	
	}

	public static boolean saveThermiRecal(Date datephoto, String threcal){
		boolean res=false;
		try {
			requete = "	UPDATE photos SET thermiquerecale= ? WHERE datephoto= ?;";
			prstate = conn.prepareStatement(requete);
			
			prstate.setString(1, threcal);
			prstate.setObject(2, datephoto);
	
			prstate.executeUpdate();
			prstate.close();
			res = true;

		} catch (SQLException e) {
			e.printStackTrace();	
			res = false;
		}finally {
				try{
         			if(prstate!=null)
            		prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
		return res;//le boolean est le cas d'erreur	
	}

	public static Vector<Photos> listePhotos(int idPlante){

		Vector<Photos> photo = new Vector<>();

		try {
			requete = "SELECT * FROM photos WHERE idplante =?;";
			prstate = conn.prepareStatement(requete);
			prstate.setObject(1, idPlante);
					
			result = prstate.executeQuery();			


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				try{
         			if(result!=null){
         				while(result.next()){
				 			photo.addElement(new Photos(result.getInt(1),
														 result.getInt(2),
														 result.getDate(3),
														 result.getString(4),
														 result.getString(5),
														 result.getString(6),
														 result.getString(7),
														 result.getFloat(8),
														 result.getFloat(9)));
						}
         				result.close();
         			}
      			} catch(SQLException se){
       				se.printStackTrace();
      			}
      			
				try{
         			if(prstate!=null)
            		prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
		return photo;//le vide est le cas d'erreur
	}
	
	public static int idPhoto(Date datephoto){
		int res = 0;
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
	    String dateFormatee = simpledate.format(datephoto);
		try {
			requete = "SELECT idphoto FROM photos WHERE datephoto >= ?;";
			prstate = conn.prepareStatement(requete);
			prstate.setObject(1, dateFormatee);
			
			result = prstate.executeQuery();
			//System.out.println(prstate);
			result.next();
			res = result.getInt(1);


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try{
         			if(result!=null)
            			result.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
				try{
         			if(prstate!=null)
            			prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
   		}			
		return res;//le 0 est le cas d'erreur
	}
	
	public static boolean saveMasque(Date datephoto, String masque, String typeMasque){
		int idphoto = idPhoto(datephoto);
		boolean res;
		//System.out.println(datephoto);
		try {
			requete = "insert into masques(idphoto,masque,typemasque,valide)values(?,?,?,false);";
			prstate = conn.prepareStatement(requete);
			prstate.setInt(1, idphoto);
			prstate.setString(2, masque);
			prstate.setString(3, typeMasque);
			
			//System.out.println(prstate);
			prstate.executeUpdate();
			
			res = true;

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally {
				try{
         			if(prstate!=null)
            			prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
      	return res;//Le boolean est le cas d'erreur
	}
	
	public static Vector<Masques> masqueNomcom(String nomcom){
		Vector<Masques> masques = new Vector<>();

		try {
			//requete = "select * from masques where idphoto IN (select idphoto from photos where datephoto IN (select datephoto from photos where idplante IN (select idplante from plantes where nomcom=?)));";
			requete = "select * from masques where idphoto IN (select idphoto from photos where idplante IN (select idplante from plantes where nomcom=?));";			
			prstate = conn.prepareStatement(requete);
			prstate.setString(1, nomcom);
			
			result = prstate.executeQuery();
			


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try{
         			if(result!=null){
         				while(result.next()){
				 			masques.addElement(new Masques(result.getInt(1),
															 result.getInt(2),
															 result.getString(3),
															 result.getString(4),
															 result.getBoolean(5)));
						}	
            			result.close();
            		}
      			}catch(SQLException se){
       				se.printStackTrace();
      			}try{
         			if(prstate!=null)
            			prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
		return masques;// Le null est le cas d'errreur
	}
	
	public static ResultSet masqueNomsci(String nomsci){
		Vector<Masques> masques = new Vector<>();

		try {
			//requete = "select * from masques where idphoto IN (select idphoto from photos where datephoto IN (select datephoto from photos where idplante IN (select idplante from plantes where nomsci=?)));";
			requete = "select * from masques where idphoto IN (select idphoto from photos where idplante IN (select idplante from plantes where nomsci=?));";
			prstate = conn.prepareStatement(requete);
			prstate.setString(1, nomsci);
			
			result = prstate.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
				try{
         			if(result!=null){
         				while(result.next()){
				 			masques.addElement(new Masques(result.getInt(1),
															 result.getInt(2),
															 result.getString(3),
															 result.getString(4),
															 result.getBoolean(5)));
						}	
            			result.close();
            		}
      			}catch(SQLException se){
       				se.printStackTrace();
      			}try{
         			if(prstate!=null)
            			prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
		return result;// Le null est le cas d'errreur
	}
	
	public static boolean updateMasque(Date datephoto, String masque, String typeMasque, boolean statut){
		int idphoto = idPhoto(datephoto);
		boolean res=false;
		try {
			requete = "update masque valide = ? where idphoto = ? and masque= ? And typemasque= ?;";
			prstate = conn.prepareStatement(requete);
			prstate.setBoolean(1, statut);
			prstate.setInt(2, idphoto);
			prstate.setString(3, masque);
			prstate.setString(4, typeMasque);
			
			result = state.executeQuery(requete);
			
			res = true;

		} catch (SQLException e) {
			e.printStackTrace();
			res = false;
		}finally {
				try{
         			if(result!=null)
  						result.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}try{
         			if(prstate!=null)
            			prstate.close();
      			}catch(SQLException se){
       				se.printStackTrace();
      			}
      	}
      	return res;//le boolean est le cas d'erreur
	}
}