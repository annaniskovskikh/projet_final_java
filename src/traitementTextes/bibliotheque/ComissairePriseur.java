package traitementTextes.bibliotheque;

/**
 * Classe qui présente les informations sur le commissaire priseur, 
 * de la personne qui gère les enchères.
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.3
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

public class ComissairePriseur {

	private ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
	private volatile int enchereCourante = 0;
	
	public ComissairePriseur() {
	}

	/**
	 *Méthode void permettant d ajouter de nouveaux acheteurs
	 *pour faire des ecnhères.
	 *@param acheteur de type Acheteur
	 */
	void addEncherisseur(Acheteur acheteur)
	{
		encherisseurList.add(acheteur);
	}
	
	/**
	 * Méthode (surchargée de la méthode présentée ci-dessous)
	 * permettant de parcourir une liste de livreEnchere,
	 * de commencer les enchères sur des livres et récupérer une liste des gagnants.
	 * @param vendeurLivresAuxEncheres
	 * @return acheteurList
	 * @throws EnchereFailure
	 */
	public ArrayList<Acheteur> StartEnchere(VendeurLivresAuxEncheres vendeurLivresAuxEncheres) throws EnchereFailure {
		ArrayList<Acheteur> acheteurList = new ArrayList<Acheteur>();
		for(var livreEnchere : vendeurLivresAuxEncheres.getLivreAVendreAuxEncheres()) {
			var winner = StartEnchere(livreEnchere);
			if(winner != null)
				acheteurList.add(winner);
		}
		return acheteurList;
	}
	
	/***
	 * Cette méthode permet de créer des Threads, les lancer,
	 * définir la gagnant et arréter des threads qui sont isAlive.
	 * @param livreEnchere
	 * @return winnerThread.getAcheteur() - objet acheteur gagnant
	 * @throws EnchereFailure
	 */
	public Acheteur StartEnchere(LivreEnchere livreEnchere) throws EnchereFailure {
			System.out.println( "La mise a prix de ce superbe livre de " + livreEnchere.getAuteur().getNom()+ " est de " + livreEnchere.getDebutEnchere());
			enchereCourante = 0;
			// Creation des threads
		   var encherisseurThreadList = creerThreads(encherisseurList,livreEnchere);
		   
		  //Start Thread
		  encherisseurThreadList.forEach(thread -> thread.start());
		  
		  // attendre fin de thread
		  var winnerThread = waitForAWinner(encherisseurThreadList);
		  if (winnerThread == null)
			  return null;
		  
		  // stopper tous les thread
		  stopAllThread(encherisseurThreadList);
		  
		  //var winnerThread = encherisseurThreadList.stream().max(Comparator.comparing(EncherisseurThread::getNumber)).orElseThrow(NoSuchElementException::new);
		  displayResult(winnerThread);
		  
		  return winnerThread.getAcheteur();
	}
	
	/***
	 * Cette méthode permet de synchroniser des threads créés.
	 * @param enchere
	 * @throws EnchereTooLowException
	 */
	synchronized void faireEnchere(int enchere) throws EnchereTooLowException
	{
		if (enchere <= enchereCourante)
		{
			throw new EnchereTooLowException(enchereCourante);
		}
		
		enchereCourante = enchere;
	}
	
	/***
	 * Cette méthode permet de créer des threads et les garder dans une liste.
	 * @param encherisseurList
	 * @param livreEnchere
	 * @return encherisseurThreadList
	 */
	private ArrayList<EncherisseurThread> creerThreads(ArrayList<Acheteur> encherisseurList,LivreEnchere livreEnchere)
	{
		   ArrayList<EncherisseurThread> encherisseurThreadList = new ArrayList<EncherisseurThread>();
		   for(var encherisseur : encherisseurList)
			   encherisseurThreadList.add(new EncherisseurThread(encherisseur, livreEnchere, this));
		   
		   for(var encherisseurThread : encherisseurThreadList)
			   encherisseurThread.setName(encherisseurThread.getAcheteur().getPrenom());
		   
		   return encherisseurThreadList;
	}
	
	/***
	 * Cette méthode permet de récuperer des threads encore "en vie".
	 * @param encherisseurThreadList
	 * @return
	 * @throws EnchereFailure
	 */
	private EncherisseurThread waitForAWinner(ArrayList<EncherisseurThread> encherisseurThreadList) throws EnchereFailure 
	{
		List<EncherisseurThread> EncherisseurThreadAlive = encherisseurThreadList;
		  while(EncherisseurThreadAlive.size() > 1)
		  {
			  try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				throw new EnchereFailure("Enchere interrompue.");
			}
			  EncherisseurThreadAlive = encherisseurThreadList.stream().filter(thread -> thread.isAlive()).collect(Collectors.toList());		  
		  }
		  
		  if (EncherisseurThreadAlive.size() > 0)
			  return EncherisseurThreadAlive.get(0);
		  else
			  return null;
	}
	
	/***
	 * Méthode permettant d arrêter tous les threads.
	 * @param encherisseurThreadList
	 */
	private void stopAllThread(ArrayList<EncherisseurThread> encherisseurThreadList)
	{
		  encherisseurThreadList.forEach((EncherisseurThread thread) -> 
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
	}
	
	/***
	 * Méthode permettant d afficher à l écran le résultat des enchères.
	 * @param winnerThread
	 */
	private void displayResult(EncherisseurThread winnerThread)
	{
		  var auteur = winnerThread.getAcheteur();
		  for(int i=1;i<4;i++)
		  {
			  try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
			 }
			 System.out.println( ""+ i + "!"); 
		  }
		  System.out.println( auteur.getPrenom() + " remporte l'enchere! Le montant de l'enchère est de " + winnerThread.getNumber() + ".");
	}
}
