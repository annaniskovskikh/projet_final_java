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
	
	private static void addAcheteur(ArrayList<EncheresThread> encheresThreadList, int debutEnchere , String name)
	{
		var encheresThread = new EncheresThread(debutEnchere, encheresThreadList.size());
		encheresThread.setName(name);
		encheresThreadList.add(encheresThread);
	}
	

	
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
		  
		  //debutEnchere --> on peut laisser nb fixe, non?
		  int debutEnchere = 10;
		  
		  Acheteur acheteur1 = new Acheteur("Pierredon", "Anaëlle", 23);
		  //int porteMonnaieAnaelle = acheteur1.setPorteMonnaie(5000);
		  Acheteur acheteur2 = new Acheteur("Niskovskikh", "Anna", 25);
		  //int porteMonnaieAnna = acheteur2.setPorteMonnaie(5000);
		  
		  ArrayList<EncheresThread> encheresThreadList = new ArrayList<EncheresThread>();
		  addAcheteur(encheresThreadList, debutEnchere, acheteur1.getPrenom());
		  addAcheteur(encheresThreadList, debutEnchere, acheteur2.getPrenom());
		  
		  /* Gestion des enchères
		  CommisseurPriseur commisseurPriseur
		  commisseurPriseur.addEncherisseur();
		  commisseurPriseur.addEncherisseur();
		  commisseurPriseur.addEncherisseur();
		  commisseurPriseur.addEncherisseur();
		  
		  commisseurPriseur.StartEnchere();*/

	      //System.out.println(thread1.getName() + " et " + thread2.getName() + " veulent acheter ce livre.");
	      
		  // Start Thread
		  encheresThreadList.forEach(thread -> thread.start());
		  Optional<EncheresThread> encheresThread = Optional.empty();  
		  while(encheresThread.isEmpty())
		  {
			  Thread.sleep(100);
			  encheresThread = encheresThreadList.stream().filter(thread -> thread.isAlive() == false).findFirst();			  
		  }

		  encheresThreadList.forEach((EncheresThread thread) -> 
		  {	
			  if (thread.isAlive() )
			  {
				  thread.interrupt();
				  
					try
					{
						  thread.join();
					}
					catch (InterruptedException e)
					{
					}
			  }
		  });
		  
		  
		  var winnerThread = encheresThreadList.stream().max(Comparator.comparing(EncheresThread::getNumber)).orElseThrow(NoSuchElementException::new);
		  var winnerName = winnerThread.getName();
		  System.out.println(winnerName + " a gagné! Le montant de l'enchère est de " + winnerThread.getNumber() + ".");
		  
		  //ici on peut calculer le reste de porteMonnaie du gagnant et 
		  //            faire une autre enchère avec les même acheteurs??
		  //c est juste un exemple pour deux acheteurs :)
		  if (winnerName == acheteur1.getPrenom()) {
			  acheteur1.setPorteMonnaie(acheteur1.getPorteMonnaie() - winnerThread.getNumber());
		  }
		  else {
			  acheteur2.setPorteMonnaie(acheteur1.getPorteMonnaie() - winnerThread.getNumber());
		  }
	}
}
	

