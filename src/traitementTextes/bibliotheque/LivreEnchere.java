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

public class LivreEnchere{
	private int debutEnchere;

	public LivreEnchere(Livre livre, int debutEnchere) {
		this.debutEnchere = debutEnchere;
		this.livre = livre;
	}
	
	public int getDebutEnchere() {
		return debutEnchere;
	}
	
	public Livre getLivre()
	{
		return livre;
	}
	
private Livre livre;
}
