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
	
	public static void main(String [] args) throws InterruptedException {
		//Juste pour tester le temps de faire une classe ThreadEnchere qui nous convient
	  
		  HashMap<Auteur, ArrayList<Livre>> catalogue = new HashMap<>();		
		  Bibliothecaire bibliothecaire = new Bibliothecaire(catalogue);
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

	

