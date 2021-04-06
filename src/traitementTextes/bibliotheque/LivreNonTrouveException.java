package traitementTextes.bibliotheque;

/**
* Classe qui contient des méthodes permettant de lancer 
* une exception si le livre pour mener des enchères 
* n est pas trouvé.
* @author Anna Niskovskikh et Anaëlle Pierredon 
* @version 1.6
*/

public class LivreNonTrouveException extends Exception {

	public LivreNonTrouveException(String errorMessage) {
		super(errorMessage);
	}

}
