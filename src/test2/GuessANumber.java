package test2;
public class GuessANumber extends Thread {
	   private boolean running;
	   private int number;
	   
	   public GuessANumber(int number) {
	      this.number = number;
	   }
	   
	   public void run() {
	      int counter = 0;
	      int guess = 0;
	      while(guess != number)
	      {
	    	 if (!running) {
		        	 System.out.println(this.getName() + " a perdu !");
		        	 break;
		      }
	    	  
	         guess = (int) (Math.random() * 100 + 1);
	         System.out.println(this.getName() + " : " + guess);
	         counter++;
	         if (guess == number) {
	        	 System.out.println("** Correct!" + this.getName() + " en " + counter + " essais.**");
	        	 break;
	         }
	      }
	   }

	public void setRunning(boolean running) {
		this.running = running;
	}
	}