package test2;

public class ExempleUtilisationThreads implements Runnable {
	   private String message;
	   
	   public ExempleUtilisationThreads(String message) {
	      this.message = message;
	   }
	   
	   public void run() {
	      while(true) {
	         System.out.println(message);
	      }
	   }
	}
