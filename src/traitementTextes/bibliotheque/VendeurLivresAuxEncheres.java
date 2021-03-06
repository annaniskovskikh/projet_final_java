package traitementTextes.bibliotheque;

/***
 * Cette interface permet à la classe Bibliothécaire 
 * de se transformer au vendeur de livres et
 * de posséder une liste de livres pour mener des enchères.
 */

import java.util.ArrayList;

public interface VendeurLivresAuxEncheres {

	ArrayList<LivreEnchere> getLivreAVendreAuxEncheres();
	
	void setEnchereEnCoursSurLivre(Livre livre, boolean etat) throws LivreNonTrouveException;
	
	void enleverLivre(Livre ancienLivre) throws LivreNonTrouveException, LivreBloquePourEncheres;
}
