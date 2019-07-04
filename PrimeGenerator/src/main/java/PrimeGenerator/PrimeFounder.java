package PrimeGenerator;

import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import Database.DB;


public class PrimeFounder {

	private ArrayList<Integer> primes = null;
	int lower, upper, threadCount;
	PrimeAlgo primeFounder;
	String mode;
	boolean error = false;
	Logger logger = LoggerFactory.getLogger(PrimeFounder.class);

	public PrimeFounder(int lower, int upper, String mode, int threadCount) {
		try {
			this.lower = Integer.valueOf(lower);
			this.upper = Integer.valueOf(upper);
			this.mode = mode;
			this.threadCount = Integer.valueOf(threadCount);

		} catch (Exception e) {
			//out.println("Unknown Arguments");
			error = true;
		}
	}

	public PrimeFounder(int lower, int upper, String mode) {
		try {
			this.lower = Integer.valueOf(lower);
			this.upper = Integer.valueOf(upper);
			this.mode = mode;
			this.threadCount = 8;

		} catch (Exception e) {

			error = true;
		}
	}

	private static String explanation() {
		String out = "";
		out += "\nApplication takes 4 inputs :\n";
		out += "lower limit (Int), upper limit (Int), algorithm (String) and thread count (Int) \n";
		out += "algorithms : \n 's' for SieveofEratosthenesPrimeFounder,\n 'b' for"
				+ " BruteForcePrimeFounder, \n 'pb' for ParallelBruteForcePrimeFounder \n";
		out += "If you use 'pb' you may give thread count (default is 8) as 4th input \n";
		out += "\nExp  : PrimeFounder 2 15 s ";
		out += "\nExp2 : PrimeFounder 2 20 pb 8 ";
		out += "\nEnjoy ! \n";
		return out;
	}

	public String[] startPrimeFounder() {

		Instant start = Instant.now();
		if (error) {
			String out = "Unknown Arguments";
			return new String[] { out + explanation() };
		}
		if (mode.equals("s")) {
			primeFounder = new SieveofEratosthenesPrimeFounder(lower, upper);
		} else if (mode.equals("b")) {
			primeFounder = new BruteForcePrimeFounder(lower, upper);
		} else if (mode.equals("pb")) {
			primeFounder = new ParallelBruteForcePrimeFounder(lower, upper, threadCount);
		} else {
			String out = "Algorithm '" + mode + "' is unknown\n";

			return new String[] { out + explanation() };
		}
		if (primeFounder.error) {
			return new String[] {"Inputs are invalid.\n\nPlease try an Upper Boundry which is greater "
					+ "than the Lower Boundry, and they both greater than 0\n"};
		}
		primes = primeFounder.getIntervalPrimes(lower, upper);
		Instant end = Instant.now();

		//out.println("Interval : " + primes);
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Duration: " + timeElapsed.toMillis() + " milliseconds");

		return new String[] { primes.toString(), timeElapsed.toString(), start.toString() };

	}

}