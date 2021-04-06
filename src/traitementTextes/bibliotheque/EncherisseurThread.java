package traitementTextes.bibliotheque;

/**
 * Classe permettant de gérer des Treads.
 * 
 * Son constructeur contient les informations suivantes :
 * @param acheteur de type Acheteur
 * @param livreEnchere de type LivreEnchere
 * @param comissairePriseur de type ComissairePriseur
 * 
 * @author Anna Niskovskikh et Anaëlle Pierredon 
 * @version 1.3
 */

public class EncherisseurThread extends Thread{
	//Volatile signifie que la valeur peut être modifiée par les différents thread
   private Acheteur acheteur;
   private LivreEnchere livreEnchere;
   private int enchereCourante = 0;
   private ComissairePriseur comissairePriseur;
   
   public EncherisseurThread(Acheteur acheteur, LivreEnchere livreEnchere, ComissairePriseur comissairePriseur) {
      this.acheteur = acheteur;
      this.livreEnchere = livreEnchere;
      this.comissairePriseur = comissairePriseur;
   }
   
   /***
    * Méthode permettant de créer des thread,
    * de vérifier si l acheteur est intéressé par le livre proposé,
    * de générer un chiffre aléatoire de 0 à 10 pour pouvoir
    * continer/ arrếter (si 0) un thread.
    */
   public void run() {
	   enchereCourante = livreEnchere.getDebutEnchere();
      do {
			try
			{
				var nomAuteur = livreEnchere.getLivre().getAuteur().getNom();
				if (acheteur.getAuteurPrefere().getNom() != nomAuteur)
		        {
		        	System.out.println( acheteur.getPrenom() + " n est pas interesse par " +  nomAuteur);
		        	break;
		        }
		        	
				Thread.sleep(1000);
				//Génère un chiffre entre 0 et 10
				 if (faireEnchere() == false) {
					 System.out.println( acheteur.getPrenom() + " ne veut plus encherir.");
					 break;
				 }
				
		         System.out.println( "Nouvelle enchere de " + acheteur.getPrenom() + " : " + getNumber() + " !");
			}
			catch (InterruptedException e)
			{
				// Activation du flag d'interruption
				Thread.currentThread().interrupt();
			}
      } while(!isInterrupted());
    }

    /***
     * Méthode permettant de récupérer la somme de l'enchère courante.
     * @return enchereCourante
     */
	public int getNumber() {
		return enchereCourante;
	}
	
	/***
	 * Méthode permettant de récupérer l acheteur associé au thread donné.
	 * @return acheteur
	 */
	public Acheteur getAcheteur()
	{
		return acheteur;
	}
	
	/***
	 * Méthode permettant de vérifier la somme d enchère courante
	 * pour qu elle ne soit pas inférieure à la somme proposé précedemment.
	 * @return boolean enchereOk
	 */
	private boolean faireEnchere()
	{
		boolean enchereOk = false;
		while(enchereOk == false)
		{
			int nouvelleEnchere = acheteur.getNouvelleEnchere(enchereCourante);
			if(nouvelleEnchere==enchereCourante)
				break;
			try {
				comissairePriseur.faireEnchere(nouvelleEnchere);
				enchereCourante = nouvelleEnchere;
				enchereOk = true;
			} catch (EnchereTooLowException e) {
				System.out.println( "L enchere de " + acheteur.getPrenom()+ " de " + nouvelleEnchere + " a echoué.");
				enchereCourante = e.getenchereCourante();
			}
		}
		
		return enchereOk;
	}
}