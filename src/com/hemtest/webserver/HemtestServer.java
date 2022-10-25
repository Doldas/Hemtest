package com.hemtest.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main webserver for the hemtest software
 *
 * @author anton lekedal (Doldas)
 */
@SpringBootApplication
public class HemtestServer {
	/**
	 * Starts the server instance with or without arguments
	 *
	 * @param args startup arguments for the spring boot server
	 */
	public void run(String[] args) {
		SpringApplication.run(this.getClass(), args);
	}
}
