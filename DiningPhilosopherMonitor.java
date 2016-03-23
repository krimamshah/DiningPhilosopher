// Monitor will ensure that a philosopher picks up both chopsticks at the same time
		
public class DiningPhilosopherMonitor {
	
	// condition variables
	private static enum State {THINKING, HUNGRY, EATING};    // The states a philosopher can be in
	private  State[] philosopherState;                       // The state of each philosopher
	private int philNum ;                                    // number of philosphers
	
	/**
	 * Creates a monitor for the right number of philosophers.
	 * Initially all philosophers are thinking.
	 * @param n number of philosophers
	 */
	public DiningPhilosopherMonitor(int n){
		this.philNum = n;
		this.philosopherState = new State[philNum];
		
		for(int i=0;i<philosopherState.length;i++){
			this.philosopherState[i] = State.THINKING;
			//System.out.println("Philosopher " + i + " is " + "THINKING");
			
		}
	}
	
	/**
	 * A philosopher picks up both chopsticks.  The philosopher waits if either
	 * neighbor is eating
	 * 
	 * @param l the philosopher who wants to eat
	 * @throws InterruptedException the thread was interrupted
	 */
	public synchronized void pickupChopsticks(int l) {
		
		this.philosopherState[l] = State.HUNGRY;
		System.out.println("Philosopher " + l + " is " + "HUNGRY");
		test(l);
		
		try {
			if (this.philosopherState[l]!= State.EATING){
            System.out.println("Philosopher " + l + " is " + "WAITING");  // Wait until neither neighbor is eating
            this.wait();   //block other threads
			}
		
		}catch (InterruptedException e) {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Put down both chopsticks.  Notify all the waiting philosophers in case one of them
	 * can now eat.
	 *  
	 * @param l the philosopher who is done eating
	 */
	
	public void putdownChopsticks(int l) {
		this.philosopherState[l] = State.THINKING;
		//System.out.println("Philosopher " + l + " is " + "THINKING");
		test((l+1)%philNum);
		test((l+4)%philNum);
	}
	
	/**
	 * check if your neighbours are eating
	 * @param k the philosopher who is wants to eat
	 */
	
	private synchronized void test(int k)  {
		
			if(
				this.philosopherState[(k+1)%philNum] != State.EATING && 
						this.philosopherState[k] == State.HUNGRY && 
								this.philosopherState[(k+4)%philNum] != State.EATING 
		  ){
			this.philosopherState[k] = State.EATING;
			this.notify();               //notify the other threads
		}
	
	}

}
