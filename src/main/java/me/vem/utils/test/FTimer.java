package me.vem.utils.test;

/**
 * This class is used to time a given method. Its simplest use is as follows:<br><br>
 * 
 * new FTimer("Some function name here", () -> someMethod()).test();<br><br>
 * 
 * This will immediately run the function someMethod() and then output the time it took, in milliseconds, to run the function in the following format:<br><br>
 * FTimer> Some function name here:<br>
 * 1.234567 milliseconds.<br><br>
 * 
 * The way it works, if you're curious, is the constructor expects a 'Runnable' type where the lambda function above is given,
 * so the lambda function is interpreted as a Runnable type, so the method you essentially override is the Runnable interface's
 * 'run()' method. FTimer's 'test()' method gets the current time in nanos, runs the Runnable, gets the new times, subtracts, and boom!
 * You've got yourself a function timer (hence: FTimer).
 */
public class FTimer {

	private final String id;
	private final Runnable func;
	
	/**
	 * A shorthand for this constructor uses Lambda functions.<br><br>
	 * 
	 * FTimer timer = new FTimer("Some Method", () -> someMethod());<br>
	 * FTimer timer2 = new FTimer("Some Method 2", () -> {
	 * 		methodA();
	 * 		methodB();
	 * 		methodZ(); 
	 * });
	 * @param id The printed ID of the FTimer.
	 * @param func The runnable (i.e. given function to test) to time.
	 */
	public FTimer(String id, Runnable func) {
		this.id = id;
		this.func = func;
	}
	
	/**
	 * Times a single test and prints it out.
	 * @return this FTimer object.
	 */
	public FTimer test() {
		test(1);
		return this;
	}
	
	/**
	 * Runs the given Runnable 'times' times, and then prints the average run time in milliseconds.
	 * @param times The number of times the function is tested.
	 * @return the average run time in milliseconds.
	 */
	public double test(int times) {
		
		long totalNanos = 0;
		
		for(int i=0;i<times;i++) {
			long start = System.nanoTime();
			func.run();
			totalNanos += System.nanoTime() - start;
		}
		
		double avgTime = totalNanos / 1000000f / times;
		System.out.printf("%n[FTimer] %s:%n%fms%n", id, avgTime);
		
		return avgTime;
	}
}
