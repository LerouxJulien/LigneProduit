import java.util.ArrayList;

import gpstl.exception.NotADirectoryException;
import gpstl.utils.LectureManifest;


public class Main {

	public static void main(String[] args) {
		ArrayList<String> listeId = new ArrayList<String>();
		
		try {
			listeId = LectureManifest.getListeId("../../plugins/");
			for(int i = 0; i < listeId.size(); i++){
				System.out.println(listeId.get(i));
			}
		} catch (NotADirectoryException e) {
			//System.out.println(e.getMessage());
		}
	}

}
