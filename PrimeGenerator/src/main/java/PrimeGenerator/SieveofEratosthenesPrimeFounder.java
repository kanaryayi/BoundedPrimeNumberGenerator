package PrimeGenerator;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.BitSet;

public class SieveofEratosthenesPrimeFounder extends PrimeAlgo {

	public SieveofEratosthenesPrimeFounder(int from, int until) {
		super(from, until);
		this.primes = findPrimes(until);
	}

	@Override
	public ArrayList<Integer> findPrimes(int until) {
		BitSet bitset = null;
		ArrayList<Integer> primes = null;

		try {
			bitset = new BitSet(until);
		} catch (Exception e) {
			out.println("Upper Boundry is invalid.\n\nPlease try a Upper Boundry "
					+ "which is greater than 0 and lower Boundry");
			return null;
		}
		for (int prime = 2; prime * prime <= until; prime++) {
			if (!bitset.get(prime)) {
				// out.println(prime);
				for (int j = prime * prime; j <= until; j += prime) {
					bitset.set(j);
				}
			}
		}

		primes = getPrimes(bitset, until);

		return primes;

	}

	public ArrayList<Integer> getPrimes(BitSet bitset, int until) {

		int clearIndex;
		ArrayList<Integer> primes = new ArrayList<>();
		// out.println(bitset);
		clearIndex = bitset.nextClearBit(2) + 1;
		while (clearIndex <= bitset.length()) {

			// out.println("found "+(clearIndex-1));
			primes.add(clearIndex - 1);
			clearIndex = bitset.nextClearBit(clearIndex) + 1;
		}
		return primes;
	}

}
