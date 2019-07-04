package Server;

public class PrimesRespond {
	private final long id;
    private final Integer[] primes;

    public PrimesRespond(long id, Integer[] primes) {
        this.id = id;
        this.primes = primes;
    }

    public long getId() {
        return id;
    }

    public Integer[] getPrimes() {
        return primes;
    }
}
