package PrimeGenerator;

import java.util.ArrayList;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParallelBruteForcePrimeFounder extends PrimeAlgo {
	int threadCount;
	Logger logger = LoggerFactory.getLogger(ParallelBruteForcePrimeFounder.class);

	private class ParallelPrimeFounder implements Runnable {
		int lastPrime, firstPrime, limit;

		public ParallelPrimeFounder(int primePerThread, int firstPrime, int limit) {
			this.lastPrime = firstPrime + primePerThread;
			this.firstPrime = firstPrime;
			this.limit = limit;
		}

		@Override
		public void run() {
			ArrayList<Integer> tempPrimes = new ArrayList<Integer>();
			for (int p = firstPrime; p < lastPrime && p < limit; p++) {
				//out.println(Thread.currentThread().getId()+" :: "+p);

				boolean isPrime = true;

				for (int j = p - 1; j > 1; j--) {
					if (p % j == 0) {
						isPrime = false;
						break;
					}
				}

				if (isPrime) {
					tempPrimes.add(p);
				}
			}
			primes.addAll(tempPrimes);
		}

	}

	public ParallelBruteForcePrimeFounder(int from, int until) {
		super(from, until);
		// 8 threads are the best optimized way in the most of devices
		this.threadCount = 8;
		this.primes = findPrimes(until);

	}

	public ParallelBruteForcePrimeFounder(int from, int until, int threadCount) {
		super(from, until);
		this.threadCount = threadCount;
		this.primes = findPrimes(until);
	}

	@Override
	public ArrayList<Integer> findPrimes(int until) {
		int primePerThread, firstPrimeForThread;
		
		primePerThread = (int) until / threadCount;
		
		if (primePerThread == 0) {
			primePerThread = until;
		}
		primePerThread += 1;
		//out.println(primePerThread);
		
		Thread[] threads = new Thread[threadCount];
		
		firstPrimeForThread = 2;
		for (int thid = 0; thid < threadCount; thid++) {
			Thread thread = new Thread(new ParallelPrimeFounder(primePerThread, firstPrimeForThread, until));
			threads[thid] = thread;
			thread.start();
			firstPrimeForThread +=primePerThread;
		}
		for (int thid = 0; thid < threadCount; thid++) {
			try {
				threads[thid].join();
			} catch (InterruptedException e) {
				logger.error("Thread error in Parallel Brute Force");
				e.printStackTrace();
			}
		}
		
		Collections.sort(primes);
		
		return primes;
	}

}
