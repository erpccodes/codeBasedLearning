package com.example.Journal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        
    	try{
    	SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        log.info("To Email: ",toEmail);
        message.setSubject(subject);
        log.info("subject : ",subject);
        message.setText(body);
        log.info("body: ",body);
        mailSender.send(message);
    }catch(Exception e) {
    	log.error("Exception while sending the email: "+e );
    }
    }
    
}
