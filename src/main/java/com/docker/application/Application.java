package com.docker.application;

import java.net.InetAddress;
import java.sql.DriverManager;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages= {"com.docker.*"})
public class Application {
	
	private static final Logger logger = LogManager.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public void loadDriver() {
		logger.debug("------loadDriver----------------");
		try {
			String ipAddr = InetAddress.getLocalHost().getHostName();
			logger.debug("Printing IP address of the host " + ipAddr);
			Map<String, String> env = System.getenv();
			for(Map.Entry<String, String> entry:env.entrySet()) {
				logger.debug(entry.getKey().concat(" = ").concat(entry.getValue()));
			}
			boolean connected = false;
			while(!connected) {
				try {
					// Note the way the postgresql container is used here.
					String url = "jdbc:postgresql://pssqldb:5432/my_database";
					//String url = "jdbc:postgresql://localhost:5432/my_database";
					String user = "postgres";
					String password = "root";
					logger.debug("Connecting to URL " + url);
					// Load the Connector/J driver
					Class class1= Class.forName("org.postgresql.Driver");
					logger.debug("Driver loaded successfully  "+class1);
					// Establish connection to MySQL
					DriverManager.getConnection(url, user, password);
					logger.debug("Connection was successful");
					connected = true;
				}catch (Exception e) {
					logger.error("Error connecting to database");
					e.printStackTrace();
					Thread.sleep(5000);
				}
				
			}
		} catch (Exception ex) {
			logger.error("Error find the user by email", ex);
		}
	}

}
