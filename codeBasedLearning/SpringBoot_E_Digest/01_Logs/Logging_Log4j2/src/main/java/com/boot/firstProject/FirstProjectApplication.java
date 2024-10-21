package com.boot.firstProject;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FirstProjectApplication {

    //private static final Logger logger = LogManager.getLogger(com.boot.firstProject.FirstProjectApplication.class);
	   private static final Logger logger = LoggerFactory.getLogger(FirstProjectApplication.class);
	public static void main(String[] args) {
		logger.info("Starting MySpringBootApplication...");
		SpringApplication.run(FirstProjectApplication.class, args);
		logger.info("MySpringBootApplication started successfully!!!");
		logger.debug("Testing the debug");
		logger.error("Testing the Error");
		System.out.println("Logger Implementation: " + logger.getClass().getName());
	}

}
