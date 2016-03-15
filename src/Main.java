import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.opencv.core.Core;

import utilBDDProjet.Requetes;

public class Main {
	 static {
		   System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		 }
	public static void main(String[] args) {

		String selectedDate = "2016-03-15";
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = simpleDateFormat.parse(selectedDate);
			System.out.println(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Requetes requetes = new Requetes();
		System.out.println(requetes.listePhotos(date));
		}
}