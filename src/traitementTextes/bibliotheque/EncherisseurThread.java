package traitementTextes.bibliotheque;

public class EncherisseurThread extends Thread{
	//Volatile signifie que la valeur peut être modifiée par les différents thread
   private Acheteur acheteur;
 private LivreEnchere livreEnchere;
   private volatile int number = 0;
   
   public EncherisseurThread(Acheteur acheteur, LivreEnchere livreEnchere) {
      this.acheteur = acheteur;
      this.livreEnchere = livreEnchere;
   }
   
   public void run() {
      int counter = 0;
      number = livreEnchere.getDebutEnchere();
      do {
			try
			{
		         Thread.sleep(1000);
				//Génère un chiffre entre 0 et 10
				 int enchere = (int) (Math.random() * 10);
				 if (enchere == 0) {
					 System.out.println( acheteur.getPrenom() + " ne veut plus encherir.");
					 break;
				 }
				 				 
				 System.out.println( acheteur.getPrenom() + " veut ajouter " + enchere );
				 
				 number = getNumber() + enchere ;
		         System.out.println( "Nouvelle enchere de " + acheteur.getPrenom() + " : " + getNumber() + " !");
		         counter++;
					}
			catch (InterruptedException e)
			{
				// Activation du flag d'interruption
				Thread.currentThread().interrupt();
			}
      } while(!isInterrupted());
    }

	public int getNumber() {
		return number;
	}
	
	public Acheteur getAcheteur()
	{
		return acheteur;
	}
}