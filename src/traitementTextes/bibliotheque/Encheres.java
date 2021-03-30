package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur les enchères 
 * comprenant 
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import projetEncheresPartie1.GuessANumber;

public class Encheres {
	private HashMap<Auteur, ArrayList<Livre>> catalogue;
	
    Bibliothecaire bibliothec = new Bibliothecaire(catalogue);
	HashMap<Auteur, ArrayList<Livre>> catalogue_livres = bibliothec.getCatalogue();
	
	/* Idées :
	 * (Je les note juste pour pas oublier, ça veut pas dire qu'on doit faire ça hein!)
	 * méthode pour choisir un livre à mettre aux enchère
	 * Pour commencer une enchère
	 * Finir l'enchère et annoncer un gagnant
	 * Que le gagnant récupère l'enchère et paye ...
	 * Penser à retirer le livre du catalogue
	 * Mettre un nombre au hasard d'acheteur donc de thread ? Si acheteur == 1 alors il gagne ?
	 * Chiffre que l'on donne aux thread = nombre de départ alors : Quand arrêter les thread?
	 * Il faudrait faire en sorte qu'à chaque tour chaque thread/acheteur "décide" ou non de continuer... proba?
	 * De combien l'acheteur enchérit ? Nombre fixe ou aléatoire ?
	 */
	
	public static void main(String [] args) throws InterruptedException {
		//Juste pour tester le temps de faire une classe ThreadEnchere qui nous convient
			
		  int debutEnchere = 10;
		  
		  EncheresThread thread1 = new EncheresThread(debutEnchere);
		  EncheresThread thread2 = new EncheresThread(debutEnchere);
	      thread1.setName("Anna");
	      thread2.setName("Anaëlle");
	      System.out.println(thread1.getName() + " et " + thread2.getName() + " veulent acheter ce livre.");
	      
	      thread1.start();
	      thread2.start();

	      boolean thread1Alive = true;
	      boolean thread2Alive = true;
	      
	      while(thread1Alive && thread2Alive) 
	      {
	    	  Thread.sleep(100);
	    	  thread1Alive = thread1.isAlive();
	          thread2Alive = thread2.isAlive();
	      }

	      if(thread1Alive)
	      {
	    	  thread1.interrupt();
	    	  thread1.join();
	    	  if (thread1.getNumber() > thread2.getNumber()) {
	    	  System.out.println(thread1.getName() + " a gagné! ");
	    	  }
	    	  else {
	    		  int dernier = (int)thread2.getNumber()+1;
	    		  System.out.println(thread1.getName() + " enchérit une dernière fois : "+ dernier + " !");
	    		  System.out.println(thread1.getName() + " a gagné! ");
	    	  }
	      }
	      else
	      {
	    	  thread2.interrupt();
	    	  thread2.join();
	    	  if (thread2.getNumber() > thread1.getNumber()) {
		    	  System.out.println(thread2.getName() + " a gagné! ");
		    	  }
		    	  else {
		    		  int dernier = (int)thread1.getNumber()+1;
		    		  System.out.println(thread2.getName() + " enchérit une dernière fois : "+ dernier + " !");
		    		  System.out.println(thread2.getName() + " a gagné! ");
		    	 }
	      }
	   }
}
	

