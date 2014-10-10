package gpstl.utils;

import gpstl.exception.InvalidManifestException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LectureManifest {
	public LectureManifest(){}
	
	public static String getIdManifest(String fichierManifest) throws FileNotFoundException, IOException, InvalidManifestException{
		try {
			InputStream ips = new FileInputStream(fichierManifest);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			while ((ligne = br.readLine()) != null){
				if(ligne.contains("Bundle-SymbolicName: ")){
					ligne = ligne.substring(ligne.indexOf("Bundle-SymbolicName: ") + 21);
					br.close(); 
					return ligne;
				}
			}
			br.close(); 
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Le fichier n'existe pas !");
		} catch (IOException e) {
			throw new IOException("Erreur de lecture du fichier !");
		}
		throw new InvalidManifestException("Le fichier n'est pas un MANIFEST.ML valide !");
	}
}
