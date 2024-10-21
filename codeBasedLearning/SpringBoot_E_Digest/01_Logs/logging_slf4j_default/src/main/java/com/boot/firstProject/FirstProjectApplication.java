package com.boot.firstProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstProjectApplication {

	public static final Logger logger=LoggerFactory.getLogger(FirstProjectApplication.class);
	public static void main(String[] args) {
		logger.info("Starting MySpringBootApplication...");
		SpringApplication.run(FirstProjectApplication.class, args);
		logger.info("MySpringBootApplication started successfully!!!");
		logger.debug("Testing the debug");
		logger.error("Testing the Error");
	}

}
