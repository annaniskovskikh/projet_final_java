package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur les enchères 
 * comprenant 
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;

import projetEncheresPartie1.GuessANumber;

public class Encheres {
	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	
    Bibliothecaire bibliothec = new Bibliothecaire(catalogue);
	HashMap<Auteur, ArrayList<Livre>> catalogue_livres = bibliothec.getCatalogue();


	
	/* Idées :
	 * (Je les note juste pour pas oublier, ça veut pas dire qu'on doit faire ça hein!)
	 * méthode pour choisir un livre à mettre aux enchère --> hm..
	 * Pour commencer une enchère ---> créer commisseurPriseur qui rajoute des acheteurs et gère une enchère?
	 * Finir l'enchère et annoncer un gagnant --> t as déjà fait ça avec 0?
	 * Que le gagnant récupère l'enchère et paye ... --> juste enlever une somme de son porteMonnaie??
	 * Penser à retirer le livre du catalogue --> bien sûr! 
	 * Mettre un nombre au hasard d'acheteur donc de thread ? Si acheteur == 1 alors il gagne ? --> pas compris ce que tu voulais dire
	 * Chiffre que l'on donne aux thread = nombre de départ alors : Quand arrêter les thread? --> avec un 0 aléatoire??
	 * Il faudrait faire en sorte qu'à chaque tour chaque thread/acheteur "décide" ou non de continuer... proba? --> toujours 0??
	 * De combien l'acheteur enchérit ? Nombre fixe ou aléatoire ? --> toujours 0? :D 
	 */
	
	public static void main(String [] args) throws InterruptedException {
		//Juste pour tester le temps de faire une classe ThreadEnchere qui nous convient
	  
		  HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		  Bibliothecaire bibliothecaire=new Bibliothecaire(catalogue);
		  /* Gestion des enchères (encherisseur = acheteur) */
		  Auteur auteur = new Auteur("Hugo");
		  ComissairePriseur comissairePriseur = new ComissairePriseur(bibliothecaire);
		  comissairePriseur.addEncherisseur(new Acheteur("Pierredon", "Anaëlle", auteur));
		  comissairePriseur.addEncherisseur(new Acheteur("Niskovskikh", "Anna", auteur));
		  comissairePriseur.StartEnchere();

	      //System.out.println(thread1.getName() + " et " + thread2.getName() + " veulent acheter ce livre.");
	      
		  
		  
		  // Il faudrait aussi vérifier que la somme proposée à superieure à celle proposée avant?
		  // anna - 10, anaëlle - 12, anna - 11 NON, Ça doit être > 12 comme anaëlle a dit 12. 
		  
		  //ici on peut calculer le reste de porteMonnaie du gagnant et 
		  //            faire une autre enchère avec les mêmes acheteurs??
		  //c est juste un exemple pour deux acheteurs :)
		  /*if (winnerName == acheteur1.getPrenom()) {
			  acheteur1.setPorteMonnaie(acheteur1.getPorteMonnaie() - winnerThread.getNumber());
		  }
		  else {
			  acheteur2.setPorteMonnaie(acheteur1.getPorteMonnaie() - winnerThread.getNumber());
		  }*/
	}
}
	

