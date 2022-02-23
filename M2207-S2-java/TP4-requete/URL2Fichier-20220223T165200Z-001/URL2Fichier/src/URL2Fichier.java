import java.net.*;
import java.io.*;
public class URL2Fichier {
	/* Méthode principale */
	public static void main(String[] args) {
		final int TAILLE_TAMPON = 4096;
		URL uneUrl ;
		InputStream fluxE=null ;
		FileOutputStream fluxS=null ;
		BufferedOutputStream donneesEcrites = null;
		BufferedInputStream donneesLues = null;
		int nbLus=0 ;
		byte [] tampon ;
		//tampon de lecture et d’écriture
		if (args.length != 2) {
			System.err.println(" Le programme requiert 2 arguments ");
			System.err.println("l'URL du fichier à copier");
			System.err.println("le nom du fichier destination");
			System.exit(1) ;
		}
		try {
			// Ouverture de l'URL pour une lecture
			uneUrl = new URL("http://www.perdu.com:8080/index.html");
			//Convertir l’URL en InputStream
			fluxE=uneUrl.openStream() ;
			//Construction du flux de type BufferedInputStream
			donneesLues = new BufferedInputStream(fluxE);
			// construction d'un FileOutputStream.
			
			fluxS = new FileOutputStream("d.txt");
			//fluxS = new FileOutputStream(args[1]);
			// construction d'un BufferedOutputStream
			donneesEcrites = new BufferedOutputStream(fluxS);
			System.out.println("Connextion établie") ;
			tampon=new byte[TAILLE_TAMPON] ;
			do{
				nbLus=donneesLues.read(tampon) ;
				if (nbLus !=-1) {
					donneesEcrites.write(tampon,0,nbLus) ;}
			}while(nbLus !=-1) ;
			donneesEcrites.close() ; //écriture de la fin du fichier
			System.out.println("Fin d’écriture") ;
		}
		catch (MalformedURLException excp) {
			System.out.println (args[0] + " : impossible de traiter cette URL");
			System.out.println (excp);
		}
		catch (IOException excp) {
			System.out.println ("Erreur : " + excp);
		}
	}
}