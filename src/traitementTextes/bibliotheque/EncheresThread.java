package traitementTextes.bibliotheque;

public class EncheresThread extends Thread{
		//Volatile signifie que la valeur peut être modifiée par les différents thread
	   volatile private int number;
	   public EncheresThread(int number) {
	      this.number = number;
	   }
	   
	   public void run() {
	      int counter = 0;
	      int enchere = 0 ;
	      do {
				try
				{
					//Génère un chiffre entre 0 et 10
					enchere = (int) (Math.random() * 10);
					
					//Si égal à 0 alors l'acheteur ne veut plus enchérir
					if (enchere == 0) {
						Thread.currentThread().interrupt();
					}
					
					 number = getNumber() + enchere ;
			         System.out.println(this.getName() + " : " + getNumber() + " !");
			         counter++;
			         Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// Activation du flag d'interruption
					Thread.currentThread().interrupt();
				}
	      } while(!isInterrupted());
	      if(enchere == 0) {
	      System.out.println(this.getName() + " s'arrête là! ");
	      }
	   }

	public int getNumber() {
		return number;
	}
}