import java.util.Random;

public class PhilosopherThread  implements Runnable{
	
	
	    private Random numGenerator = new Random();   // Used to vary how long a philosopher thinks before eating and how long the philosopher eats
		private DiningPhilosopherMonitor monitor;     // Controls when a philosopher can pick up chopsticks
		private int i;                                // The philosopher's unique id
		
		public PhilosopherThread(){
			
		}

		/**
		 * Constructs a new philosopher
		 * @param i the unique id
		 * @param monitor the object that controls picking up chopsticks
		 */

		public PhilosopherThread(DiningPhilosopherMonitor monitor, int i) {
		    this.monitor = monitor;
		    this.i = i;
		}

		/**
		 * Repeatedly think, pick up chopsticks, eat and put down chopsticks
		 */
		
		@Override
		public void run() {
			try {
		    

		    	while (!Thread.interrupted()){
		    		think();
					monitor.pickupChopsticks(i);
					eat();
	                monitor.putdownChopsticks(i);
		    	
		    }
			} catch (InterruptedException e) {// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		/**
		 * Lets a random amount of time pass to model eating.
		 * @throws InterruptedException
		 */

		private void eat() throws InterruptedException {
			System.out.println("Philosopher " + this.i + " is EATING.\n");
			System.out.flush();
			Thread.sleep (numGenerator.nextInt(10));
		}
		
		/**
		 * Lets a random amount of time pass to model thinking.
		 * @throws InterruptedException
		 */
		private void think() throws InterruptedException {
			System.out.println("Philosopher " + this.i + " is " + "THINKING.\n");
			System.out.flush();
			Thread.sleep (numGenerator.nextInt(10));
		}
		
}


