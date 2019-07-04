package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DB {
	String url;

	Logger logger = LoggerFactory.getLogger(DB.class);
	
	public void createDatabase(String fileName) {

		url = "jdbc:sqlite:../../" + fileName;

		try (Connection connection = DriverManager.getConnection(url)) {
			if (connection != null) {
				logger.info("Database Succesfully created.");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

	}

	public void createATable() {
		// SQL statement for creating a new table

		String sql = "CREATE TABLE IF NOT EXISTS requests(" 
		+ "	id INTEGER PRIMARY KEY,"		
		+ "	lower INTEGER," 
		+ "	upper INTEGER," 
		+ "	totalprimes INTEGER," 
		+ "	timeelapsed TEXT,"
		+ "	timestamp TEXT," 
		+ "	algorithm TEXT" 
		+ ")";

		// String sql = "CREATE TABLE mytable (mycolumn TEXT, myothercolumn INTEGER)";
		try (Connection connection = DriverManager.getConnection(url);
				Statement statement = connection.createStatement()) {
			statement.execute(sql);
		} catch (SQLException e) {
			logger.error("TABLE ERROR");
			logger.error(e.getMessage());
		}
	}

	public void insert(Integer lower, Integer upper, Integer totalprimes, String timeelapsed, String timestamp,
			String algorithm) {
		String sql = "INSERT INTO requests(lower,upper,totalprimes,timeelapsed,timestamp,algorithm) "
				+ "VALUES(?, ?, ?, ?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(url)) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, lower);
			statement.setInt(2, upper);
			statement.setInt(3, totalprimes);
			statement.setString(4, timeelapsed);
			statement.setString(5, timestamp);
			statement.setString(6, algorithm);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("INSERT ERROR");
			logger.error(e.getMessage());
		}
	}

	public void show() {
		String sql = "SELECT * FROM requests";

		try (Connection connection = DriverManager.getConnection(url)) {
			Statement statement = connection.createStatement();
			ResultSet res = statement.executeQuery(sql);
			// loop through the result set
			String out= "\n";
			while (res.next()) {
				out += res.getInt("id") + "\t" + res.getInt("lower") + "\t" + res.getInt("upper") + "\t"
						+ res.getInt("totalprimes") + res.getString("timeelapsed") + "\t" + res.getString("timestamp")
						+ "\t" + res.getString("algorithm")+"\n";
			}
			logger.info(out);
		} catch (SQLException e) {
			logger.error("SHOW ERROR");
			logger.error(e.getMessage());
		}
	}
}