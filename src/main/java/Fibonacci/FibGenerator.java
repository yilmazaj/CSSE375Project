package Fibonacci;

public class FibGenerator {
	
	public static int fib(int x) {
		if(x < 0) { 
			throw new IllegalArgumentException("x cannot be negative");
		} else if(x == 0) {
			return 0;
		} else if(x == 1) {
			return 1;
		} else if(x > 1 && x < 47) {
			return fib(x - 1) + fib(x - 2);
		} else
			throw new IllegalArgumentException("x cannot be greater than 46");
	}

}
