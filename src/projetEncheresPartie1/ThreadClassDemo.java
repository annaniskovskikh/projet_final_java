package projetEncheresPartie1;

public class ThreadClassDemo {

	   public static void main(String [] args) {
	      Thread thread = new GuessANumber(27);
	      Thread thread2 = new GuessANumber(27);
	      thread.setName("Le premier utilisateur");
	      thread2.setName("Le deuxième utilisateur");
	      System.out.println(thread.getName() + " commence...");
	      thread.start();
	      System.out.println(thread2.getName() + " commence...");
	      thread2.start();
	      
	      try {
	         thread.join();
	      } catch (InterruptedException e) {
	         System.out.println("Thread interrompu.");
	      }
	      
	      if(thread.isAlive() && !thread2.isAlive()) {
	    	  thread2.interrupt();
	    	  System.out.println("!!! " + thread.getName() + " a gagné.");
	      }
	      else {
	    	  thread.interrupt();
	    	  System.out.println("!!! " + thread2.getName() + " a gagné.");
	      }
	   }
	}
