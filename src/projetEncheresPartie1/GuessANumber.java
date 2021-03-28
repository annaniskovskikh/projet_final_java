package projetEncheresPartie1;

public class GuessANumber extends Thread {
	   private int number;
	   public GuessANumber(int number) {
	      this.number = number;
	   }
	   
	   public void run() {
	      int counter = 0;
	      int guess = 0;
	      do {
				try
				{
					guess = (int) (Math.random() * 10 + 1);
			         System.out.println(this.getName() + " dit : " + guess);
			         counter++;
			         Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// Activation du flag d'interruption
					Thread.currentThread().interrupt();
				}
	      } while(guess != number && ! isInterrupted());
	      
	      if (guess == number )
	    	  System.out.println("** Correct! " + this.getName() + " deviné en " + counter + " essais.**");
	   }
	}