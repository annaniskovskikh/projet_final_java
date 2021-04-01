package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations pour le travailleur,
 * Héritée de la classe Lecteur
 * @author Anna Niskovskikh, Anaëlle Pierredon et Qi Wang
 * @version 1.4
 */

public class Travailleur extends Lecteur {

	/**
	 * Constructeur de la classe Travailleur
	 * @param nom Nom de famille du travailleur
	 * @param prenom Prenom du travailleur
	 * @param age Age du travailleur
	 */
	public Travailleur(String nom, String prenom) {
		super(nom, prenom);
	}

	/**
	 * Méthode pour renvoyer sa catégorie socioprofessionnelle
	 * donc Travailleur pour la classe Travailleur
	 * @return CategorieSocioProfessionelle.Travailleur
	 */
	@Override
	public CategorieSocioProfessionelle GetCategorie() {
		return CategorieSocioProfessionelle.Travailleur;
	}

}
