package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations 
 * sur le livre pour les enchères 
 * comprenant autre et titre
 * hérités de la classe Livre
 * et debutEnchere présenant la somme de départ.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public class LivreEnchere extends Livre{
	private int debutEnchere;

	public LivreEnchere(Auteur auteur, String titre, int debutEnchere) {
		super(auteur, titre);
		this.debutEnchere = debutEnchere;
	}
	
	public int getDebutEnchere() {
		return debutEnchere;
	}
}
