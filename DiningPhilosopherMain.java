// The Dining Philosopher's problem using Monitor


public class DiningPhilosopherMain {
	
	public static final int NB_OF_PHILOSOPHERS = 5;

	public static void main(String[] args) throws InterruptedException {
		
		DiningPhilosopherMonitor dp = new DiningPhilosopherMonitor(NB_OF_PHILOSOPHERS);
		 
		    for (int i=0; i<NB_OF_PHILOSOPHERS; i++) {
		        new Thread(new PhilosopherThread(dp, i)).start(); // Create a thread
	}

}
}	
