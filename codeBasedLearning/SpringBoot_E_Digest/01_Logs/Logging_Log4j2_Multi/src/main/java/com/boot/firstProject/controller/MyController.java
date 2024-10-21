package com.boot.firstProject.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    
	 // Create a logger for this class
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping("/api/test")
    public String getApiLogs() {
    	
        // Example log messages at different levels
        logger.info("This is an info log message inside controller.");
        logger.debug("This is a debug log message inside controller.");
        logger.error("This is an error log message inside controller.");
        
    	return "checking for Api Logs";
    }
}
