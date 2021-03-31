package traitementTextes.bibliotheque;

public class ThreadNotAliveException extends Exception {

	private int threadNumber;
	public ThreadNotAliveException(int threadNumber) {
		this.threadNumber = threadNumber;;
	}

	public int getThreadNumber(){
		return threadNumber;
	}
}
