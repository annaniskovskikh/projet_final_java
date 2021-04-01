package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur l'acheteur 
 * héritée de la classe Personne
 * ainsi que porteMonnaie de l'acheteur
 * et son auteurPrefere.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public class Acheteur extends Personne{
	private int porteMonnaie;
	private Auteur auteurPrefere;
	
	/**
	 * Constructeur de la classe Acheteur
	 * @param nom Nom de famille de l'acheteur venant de la classe Personne
	 * @param prenom Prenom de l'acheteur venant de la classe Personne
	 * @param age Age de l'acheteur venant de la classe Personne
	 * @param porteMonnaie le porte monnaie de l'acheteur
	 */
	public Acheteur(String nom, String prenom, Auteur auteurPrefere) {
		super(nom, prenom);
		this.auteurPrefere = auteurPrefere;
	}
	
	public int getPorteMonnaie() {
		return porteMonnaie;
	}
	
	public void setPorteMonnaie(int porteMonnaie) {
		this.porteMonnaie = porteMonnaie;
	}
	
	public Auteur getAuteurPrefere() {
		return auteurPrefere;
	}
	
	public void setAuteurPrefere(Auteur auteurPrefere) {
		this.auteurPrefere = auteurPrefere;
	}

}
