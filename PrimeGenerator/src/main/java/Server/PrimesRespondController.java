package Server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Database.DB;
import PrimeGenerator.PrimeFounder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class PrimesRespondController {
	DB db;
	Logger logger = LoggerFactory.getLogger(PrimesRespondController.class);

	public PrimesRespondController() {
		db = new DB();
		db.createDatabase("PrimeGenerator.db");
		db.createATable();
		logger.info("controller is constructed");
	}
	@GetMapping(path = "/help") // Map ONLY Get Requests
	public @ResponseBody String help() {
		
		String out = "";
		out += "\nApplication takes 4 inputs :\n";
		out += "lower limit (Int), upper limit (Int), algorithm (String) and thread count (Int) \n";
		out += "algorithms : \n 's' for SieveofEratosthenesPrimeFounder,\n 'b' for"
				+ " BruteForcePrimeFounder, \n 'pb' for ParallelBruteForcePrimeFounder \n";
		out += "If you use 'pb' you may give thread count (default is 8) as 4th input \n";
		out += "\nTo use Http Restful service please look at below examples/n";
		out += "\nExp  : curl -d \"lower=0&upper=5&algo=b\" -X POST localhost:8080/getprimes/";
		out += "\nExp2 : curl -d \"lower=0&upper=5&algo=pb&threadCount=8\" -X POST localhost:8080/getprimes/";
		out += "\nEnjoy ! \n";
		logger.info(out);
		return out;
	}
	@PostMapping(path = "/getprimes") // Map ONLY Post Requests
	public @ResponseBody String generatePrimes(@RequestParam Integer lower, @RequestParam Integer upper,
			@RequestParam String algo) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		PrimeFounder generator = new PrimeFounder(lower, upper, algo);
		String[] result = generator.startPrimeFounder();
		saveIt(result, lower, upper, algo);

		return result[0];
	}
	public @ResponseBody String generatePrimes(@RequestParam Integer lower, @RequestParam Integer upper,
			@RequestParam String algo, @RequestParam Integer threadCount) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		PrimeFounder generator = new PrimeFounder(lower, upper, algo, threadCount);
		String[] result = generator.startPrimeFounder();
		saveIt(result, lower, upper, algo);

		return result[0];
	}

	private void saveIt(String[] result, Integer lower, Integer upper, String algo) {
		Integer totalprimes = result.length;
		if(result.length > 1) {
			db.insert(lower, upper, totalprimes,result[1], result[2], algo);
			
		}
		db.show();
	}
}
