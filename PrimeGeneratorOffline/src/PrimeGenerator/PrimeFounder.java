package PrimeGenerator;

import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;

import static java.lang.System.out;

public class PrimeFounder {

	public PrimeFounder() {

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
	public static void main(String[] args) {
		int lower, upper, threadCount;
		ArrayList<Integer> primes;
		PrimeAlgo primeFounder;
		String mode;
		try {
			lower = Integer.valueOf(args[0]);
			upper = Integer.valueOf(args[1]);
			mode = args[2];
		}
		catch(Exception e){
			out.println("Unknown Arguments");
			out.println(explanation());
			return;
		}
		finally {
			try {
				threadCount = Integer.valueOf(args[3]);
			}
			catch (Exception e) {
				threadCount = 8;
			}
		}
		

		Instant start = Instant.now();
		
		if (mode.equals("s")) {
			primeFounder = new SieveofEratosthenesPrimeFounder(lower, upper);
		} else if (mode.equals("b")) {
			primeFounder = new BruteForcePrimeFounder(lower, upper);
		} else if (mode.equals("pb")) {
			primeFounder = new ParallelBruteForcePrimeFounder(lower, upper, threadCount);
		} else {
			out.println("Algorithm '"+mode+"' is unknown");
			out.println(explanation());
			return;
		}

		primes = primeFounder.getIntervalPrimes(lower, upper);
		Instant end = Instant.now();

		out.println("Interval : " + primes);
		Duration timeElapsed = Duration.between(start, end);
		System.out.println("Duration: " + timeElapsed.toMillis() + " milliseconds");
		// out.println("All : "+primeFounder.primes);

	}

}