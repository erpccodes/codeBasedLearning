package com.example.Journal.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Journal.service.EmailService;

@SpringBootTest
public class EmailServiceTest {

	@Autowired
	private EmailService emailService;
	
	@Test
	public void sendEmailTest() {
		emailService.sendEmail("erprincechat0007@gmail.com", "Test mail from Springboot", "Testing Email from Springboot Journal Application");
	}
}
