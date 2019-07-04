package PrimeGenerator;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Collections;

public abstract class PrimeAlgo {

	protected ArrayList<Integer> primes = new ArrayList<>();

	public PrimeAlgo(int until) {

		this.primes = findPrimes(until);
	}
	
	public PrimeAlgo(int from, int until) {
		if(!isValidInputs(from, until)) {
			return;
		}
		
	}

	public abstract ArrayList<Integer> findPrimes(int until);

	public ArrayList<Integer> getIntervalPrimes(int from, int until) {
		ArrayList<Integer> intPrimes = new ArrayList<>();
		int lower;

		// binary search to decrease search time
		System.out.println(primes);
		lower = Collections.binarySearch(primes, from + 1);

		// out.println(lower);

		// Searched key not found
		if (lower < 0) {
			lower = -(lower + 1);
		}

		// out.println(lower+" "+until);

		// Iterate till reach to upper boundry
		for (int i = lower; i < primes.size() && primes.get(i) < until; i++) {
			intPrimes.add(primes.get(i));
		}

		return intPrimes;
	}

	public boolean isValidInputs(int from, int until) {
		try {
			if (until < 0 || from < 0 || from >= until) {
				return false;
			}
		} catch (Exception e) {
			out.println("Inputs are invalid.\n\nPlease try a Upper Boundry which is greater "
					+ "than Lower Boundry, and they both greater than 0");
			return false;
		}
		return true;
	}
}
