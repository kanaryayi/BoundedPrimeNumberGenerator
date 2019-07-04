package PrimeGenerator;



import java.util.ArrayList;

import static java.lang.System.out;

public class BruteForcePrimeFounder extends PrimeAlgo {

	public BruteForcePrimeFounder(int from, int until) {
		super(from, until);
		this.primes = findPrimes(until);
	}

	@Override
	public ArrayList<Integer> findPrimes(int until) {
		
		for (int p = 2; p < until; p++) {

			boolean isPrime = true;

			for (int j = p - 1; j > 1; j--) {
				//out.println(p+" "+j);
				if (p % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				primes.add(p);
			}
		}

		return primes;
	}

}