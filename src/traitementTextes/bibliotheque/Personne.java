package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations pour la personne 
 * comprenant son nom, prenom et l'age.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

public abstract class Personne {
	private String nom;
	private String prenom;
	private int age;

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
