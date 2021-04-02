package traitementTextes.bibliotheque;

/**
* Classe qui contient des méthodes permettant de lancer 
* une exception si la somme courante des enchères est inférieure
* à celle précedente.
* @author Anna Niskovskikh et Anaëlle Pierredon 
* @version 1.5
*/

public class EnchereTooLowException extends Exception {

	private int enchereCourante=0;
	public EnchereTooLowException(int enchereCourante) {
		this.enchereCourante = enchereCourante;
	}

	/***
	 * Méthode permettant de récupérer la somme 
	 * de l enchère courante d un acheteur.
	 * @return enchereCourante
	 */
	int getenchereCourante()
	{
		return enchereCourante;
	}
}
