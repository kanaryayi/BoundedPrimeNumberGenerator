package Server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Database.DB;

@SpringBootApplication
public class ServerStart {

	public static void main(String[] args) {
		SpringApplication.run(ServerStart.class, args);
	}
	public boolean ApplicationFunc() {
		return true;
	}
}
