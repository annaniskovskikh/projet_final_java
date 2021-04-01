package traitementTextes.bibliotheque;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ComissairePriseur {

	private VendeurLivresAuxEncheres vendeurLivresAuxEncheres;
	private ArrayList<Acheteur> encherisseurList = new ArrayList<Acheteur>();
	
	public ComissairePriseur(VendeurLivresAuxEncheres vendeurLivresAuxEncheres) {
		this.vendeurLivresAuxEncheres = vendeurLivresAuxEncheres;
	}

	void addEncherisseur(Acheteur acheteur)
	{
		encherisseurList.add(acheteur);
	}
	
	public void StartEnchere() {
		for(var livreEnchere : vendeurLivresAuxEncheres.getLivreAVendreAuxEncheres())
			//vérifier s il y a des livres pour un auteur préféré des acheteurs?
			StartEnchereLivre(livreEnchere);
	}
	
	public EncherisseurThread StartEnchereLivre(LivreEnchere livreEnchere) {
			System.out.println( "La mise a prix de ce superbe livre est de " + livreEnchere.getDebutEnchere());   
			// Creation des threads
		   ArrayList<EncherisseurThread> encherisseurThreadList = new ArrayList<EncherisseurThread>();
		   for(var encherisseur : encherisseurList)
			   encherisseurThreadList.add(new EncherisseurThread(encherisseur, livreEnchere));
		   
		   for(var encherisseurThread : encherisseurThreadList)
			   encherisseurThread.setName(encherisseurThread.getAcheteur().getPrenom());
		   
		  //Start Thread
		  encherisseurThreadList.forEach(thread -> thread.start());
		  
		  // attendre fin de thread
		  Optional<EncherisseurThread> encheresThread = Optional.empty();  
		  while(encheresThread.isEmpty())
		  {
			  try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			  encheresThread = encherisseurThreadList.stream().filter(thread -> thread.isAlive() == false).findFirst();			  
		  }

		  // stopper tous les thread
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
		  
		  
		  var winnerThread = encherisseurThreadList.stream().max(Comparator.comparing(EncherisseurThread::getNumber)).orElseThrow(NoSuchElementException::new);
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
	
		  return winnerThread;
	}
	
}
