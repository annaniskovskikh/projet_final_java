package traitementTextes.bibliotheque;

/**
* Classe qui contient des méthodes permettant de lancer 
* une exception de l exécution des enchères.
* @author Anna Niskovskikh et Anaëlle Pierredon 
* @version 1.5
*/

public class EnchereFailure extends Exception {

	public EnchereFailure(String errorMessage) {
		super(errorMessage);
	}

}
