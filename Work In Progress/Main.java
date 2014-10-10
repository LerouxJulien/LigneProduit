import java.io.IOException;

import gpstl.exception.InvalidManifestException;
import gpstl.utils.LectureManifest;


public class Main {

	public static void main(String[] args) {
		try {
			System.out.println(LectureManifest.getIdManifest("MANIFEST.MF"));
		} catch (IOException | InvalidManifestException e) {
			System.out.println(e.getMessage());
		}
	}

}
