package com.example.Journal.schedular;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Journal.entity.Journal;
import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepositoryImp;
import com.example.Journal.service.EmailService;
import com.example.Journal.service.SentimentAnalysisService;
import com.example.Journal.service.SentimentAnalysisServiceR1;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailScheduler {

	@Autowired
	private UserRepositoryImp repositoryImp;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired 
	private SentimentAnalysisServiceR1 analysisService;
	
	//Running every minute
	//@Scheduled(cron = "0 * * * * *")
	//Monday at 10:00 AM
	@Scheduled(cron = "0 0 10 * * MON")
	public void fetchUserAndSendEmail() {
		
		log.info("Inside Scheduler");
		
		List<User> users=repositoryImp.getUserForSA();
		log.info("Inside Scheduler users: "+users);
		for(User user:users) {
			List<Journal> journal=user.getJournalEntries();
			List<String> filteredJournal=journal.stream().filter(x->x.getDate()
					.isAfter(LocalDateTime.now().minus(365,ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
			String entries=String.join(" ", filteredJournal);
			String analysisText=analysisService.getSentimentAnalysisR1(entries);
			log.info("Inside Scheduler, Inside Loop, analyzedText : "+analysisText);
			emailService.sendEmail(user.getEmail(),"Weekly Sentiment Analysis of Journals" , analysisText);
		}
	}
	
}
