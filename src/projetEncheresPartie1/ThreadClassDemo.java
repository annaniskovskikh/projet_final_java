package projetEncheresPartie1;

public class ThreadClassDemo {

	public static void main(String [] args) throws InterruptedException {
		
	      Thread thread = new GuessANumber(2);
	      Thread thread2 = new GuessANumber(2);
	      thread.setName("Le premier utilisateur");
	      thread2.setName("Le deuxième utilisateur");
	      System.out.println(thread.getName() + " commence...");
	      thread.start();
	      System.out.println(thread2.getName() + " commence...");
	      thread2.start();

	      boolean thread1Alive = true;
	      boolean thread2Alive = true;
	      while(thread1Alive && thread2Alive) 
	      {
	    	  Thread.sleep(100);
	    	  thread1Alive = thread.isAlive();
	          thread2Alive = thread2.isAlive();
	      }

	      if(thread1Alive)
	      {
	    	  thread.interrupt();
	    	  thread.join();
	    	  System.out.println(thread2.getName() + " a gagné! ");
	      }
	      else
	      {
	    	  thread2.interrupt();
	    	  thread2.join();
	    	  System.out.println(thread.getName() + " a gagné! ");
	      }
	   }
	}