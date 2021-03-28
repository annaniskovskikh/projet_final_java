package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations pour l'acheteur 
 * comprenant 
 * aussi avec l'héritage de la classe Personne.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public class Acheteur extends Personne{
	private int porteMonnaie;
	

	public Acheteur(String nom, String prenom, int age ) {
		super(nom, prenom, age);
	}
	
	public int getPorteMonnaie() {
		return porteMonnaie;
	}
	
	public void setPorteMonnaie(int porteMonnaie) {
		this.porteMonnaie = porteMonnaie;
	}

}
