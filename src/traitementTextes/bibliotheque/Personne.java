package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur la personne 
 * comprenant son nom, prenom et l'age.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public abstract class Personne {
	private String nom;
	private String prenom;
	private int age;

	/**
	 * Constructeur de la classe Personne
	 * @param nom Nom de famille de la personne 
	 * @param prenom Prenom de la personne 
	 * @param age Age de la personne 
	 */
	public Personne(String nom, String prenom, int age ) {
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
	}

	public String getNom() {
		return nom;
	}
	
	public String getPrenom() {
		return prenom;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
