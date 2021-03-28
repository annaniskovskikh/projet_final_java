package test2;
public class ThreadClassDemo {

	public static void main(String [] args) {

	      System.out.println("Le premier utilisateur commence...");
	      GuessANumber thread1 = new GuessANumber(27);
	      System.out.println("Le deuxième utilisateur commence...");
	      GuessANumber thread2 = new GuessANumber(27);
	      
	      thread1.setName("Premier utilisateur");
	      thread2.setName("Deuxième utilisateur"); 
	      
	      thread1.setRunning(true);
	      thread2.setRunning(true);
	      
	      thread1.start();
	      thread2.start();
	      
	      
	      try {
	         thread1.join();
	      } catch (InterruptedException e) {
	         System.out.println("Thread interrompu.");
	      }
	      if (thread1.isAlive() && !thread2.isAlive()) {
	    	  thread1.interrupt();
	    	  thread1.setRunning(false);
	    	  System.out.println(thread2.getName() + " a gagné.");
	      }
	      else {
	    	  thread2.interrupt();
	    	  thread2.setRunning(false);
	    	  System.out.println(thread1.getName() + " a gagné. ");
	      }
	      System.out.println("le main() s'arrête...");
	   }
	}