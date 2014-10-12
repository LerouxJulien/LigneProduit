package gpstl.utils;

import gpstl.exception.InvalidManifestException;
import gpstl.exception.NotADirectoryException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.jar.JarFile;

public class LectureManifest {
	public LectureManifest(){}
	
	public static ArrayList<String> getListeId(String repertoirePlugin) throws NotADirectoryException{
		ArrayList<String> res = new ArrayList<String>();
		File r = new File(repertoirePlugin);
		
		if(r.isDirectory()){
			String[] liste = r.list();
			for(int i = 0; i < liste.length; i++){
				if(new File(repertoirePlugin + liste[i]).isDirectory()){
					try {
						res.add(getIdManifest(repertoirePlugin + liste[i] + "/META-INF/MANIFEST.MF"));
					} catch (IOException | InvalidManifestException e) {
						//System.out.println(e.getMessage());
					}
				} else {
					if(liste[i].endsWith(".jar")){
						try {
							res.add(getIdManifestJar(repertoirePlugin + liste[i]));
						} catch (IOException e) {
							//System.out.println(e.getMessage());
						}
					}
				}
			}
		} else {
			throw new NotADirectoryException(repertoirePlugin + " n'est pas un repertoire !");
		}
		return res;
	}
	
	private static String getIdManifest(String fichierManifest) throws FileNotFoundException, IOException, InvalidManifestException{
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
			throw new FileNotFoundException("Le fichier " + fichierManifest + " n'existe pas !");
		} catch (IOException e) {
			throw new IOException("Erreur de lecture du fichier !");
		}
		throw new InvalidManifestException("Le fichier n'est pas un MANIFEST.MF valide !");
	}
	
	private static String getIdManifestJar(String fichierJar) throws IOException{
		File f = new File(fichierJar);
		try {
			return new JarFile(f).getManifest().getMainAttributes().getValue("Bundle-SymbolicName");
		} catch (IOException e) {
			throw new IOException("Erreur de lecture de " + fichierJar + " !");
		}
	}
}
