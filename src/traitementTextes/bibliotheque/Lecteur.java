package traitementTextes.bibliotheque;

import java.util.Comparator;

/**
 * Énumérer 2 catégories socio-professionnelles pour le lecteur
 * donc Travailleur et Etudiant.
 */

enum CategorieSocioProfessionelle
{
	Travailleur,
	Etudiant
};

/**
 * Classe qui présente les informations du lecteur,
 * Héritée de la classe Personne
 * @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
 * @version 1.4
 */

public abstract class Lecteur extends Personne{
	
	private Amende amende;
	private boolean livreEnRetard;

	/**
	 * Constructeur de la classe Lecteur
	 * @param nom Nom de famille de lecteur
	 * @param prenom prenom de lecteur
	 */
	public Lecteur(String nom, String prenom) {
		super(nom, prenom);
	}

	/**
	 * Méthode equals pour vérifier qu'un objet donné, donc lecteur est bien une instance de Lecteur 
	 * et ensuite vérifier que cette instance est de type Lecteur
	 * @param lecteur Un objet lecteur
	 * @return true L'objet est bien une instance de Lecteur ou Cette instance est de type Lecteur.
	 * @return false Si l'objet n'est pas une instance de Lecteur, et l'instance n'est pas de type Lecteur.
	 */
	@Override
	public boolean equals(Object lecteur) {
		if (this == lecteur) {
			return true;
		}
		if (lecteur instanceof Lecteur) {
			return this.getNom().equals(((Lecteur) lecteur).getNom());
		}
		return false;

	}
	
	abstract public CategorieSocioProfessionelle GetCategorie();
	
	
	public Amende getAmende() {
		return amende;
	}

	public void setAmende(Amende amende) {
		this.amende = amende;
	}
	
	public boolean aLivreEnRetard() {
		return livreEnRetard;
	}

	public void setLivreEnRetard(boolean livreEnRetard) {
		this.livreEnRetard = livreEnRetard;
	}
	
	public double paieAmende(Amende amende)
	{
		return amende.getAmende();
	}
	
	public void afficherID(Lecteur lecteur) {
		System.out.println(lecteur.getNom()+lecteur.getPrenom());
	}
}

/**
 * Classe pour comparer deux lecteurs 
 * au niveau de leur nom en ASCII
 * @return lecteur1.getNom().compareTo(lecteur2.getNom())
 */
class CompareLecteur implements Comparator<Lecteur>{
	public int compare(Lecteur lecteur1, Lecteur lecteur2) {
		return lecteur1.getNom().compareTo(lecteur2.getNom());
	}
}