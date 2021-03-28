package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur l'acheteur 
 * comprenant porteMonnaie de l'acheteur,
 * héritée de la classe Personne.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public class Acheteur extends Personne{
	private int porteMonnaie;
	
	/**
	 * Constructeur de la classe Acheteur
	 * @param nom Nom de famille de l'acheteur venant de la classe Personne
	 * @param prenom Prenom de l'acheteur venant de la classe Personne
	 * @param age Age de l'acheteur venant de la classe Personne
	 */
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
