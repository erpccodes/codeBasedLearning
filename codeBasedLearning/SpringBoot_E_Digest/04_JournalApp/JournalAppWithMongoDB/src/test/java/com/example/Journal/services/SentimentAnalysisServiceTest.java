package com.example.Journal.services;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Journal.entity.Journal;
import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepositoryImp;
import com.example.Journal.service.SentimentAnalysisService;
import com.example.Journal.service.SentimentAnalysisServiceR1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class SentimentAnalysisServiceTest {
	
@Autowired
private UserRepositoryImp repositoryImp;

@Autowired
private SentimentAnalysisServiceR1 analysisService;

	@Test
	public void getSentimenAnalysisOfUserTest() {
		List<User> users=repositoryImp.getUserForSA();
		log.info("Inside Scheduler users: "+users);
		for(User user:users) {
			List<Journal> journal=user.getJournalEntries();
			log.info("Journal Entries: "+journal);
			List<String> filteredJournal=journal.stream().filter(x->x.getDate()
					.isAfter(LocalDateTime.now().minus(365,ChronoUnit.DAYS))).map(x->x.getContent()).collect(Collectors.toList());
			log.info("filtered Journal: "+filteredJournal);
			String entries=String.join(" ", filteredJournal);
			String analysisText=analysisService.getSentimentAnalysisR1(entries);
			log.info("Entries: "+entries);
			log.info("Inside Scheduler, Inside Loop, analyzedText : "+analysisText);
	}
		
}
}
