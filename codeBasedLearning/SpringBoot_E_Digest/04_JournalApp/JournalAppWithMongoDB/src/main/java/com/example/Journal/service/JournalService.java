package com.example.Journal.service;

import java.time.LocalDateTime;
import java.util.List;


import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Journal.entity.Journal;
import com.example.Journal.entity.User;

import com.example.Journal.repository.JournalRepository;
import com.example.Journal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JournalService {

	@Autowired
	private JournalRepository journalRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public ResponseEntity<Journal> saveJournalEntryOfUser(Journal journal,String username) {
		// save will update if id is already present but insert does not allows duplicates
		//journalRepository.save(journal);
		try {
			journal.setDate(LocalDateTime.now());
			Journal insertedJournal= journalRepository.insert(journal);
			User user=userRepository.findByUserName(username);
			user.getJournalEntries().add(insertedJournal);
			userRepository.save(user);
			log.info("Inside saveJournalEntryOfUser, Journal entry {} saved successfully for user: {}",journal,user);
			return new ResponseEntity<>(journal,HttpStatus.OK); 
		}catch(Exception e) {
			log.error("Error saving Journal entry for user: {} - {}",username,e.getMessage());
			return new  ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}	
		
	
	public ResponseEntity<?> getAllJournalEntriesOfUser(String username){
		User user=userRepository.findByUserName(username);
		List<Journal> journals=user.getJournalEntries();
		if(journals!=null && !journals.isEmpty()) {
			log.info("Inside getJournalEntriesOfUser, fetching journal entries"+journals);
			return new ResponseEntity<>(journals,HttpStatus.OK);
		}else { 
			log.info("Inside getJournalEntriesOfUser, No Journal found for user: "+username);
			return new ResponseEntity<>("No Journal for this User",HttpStatus.NOT_FOUND);
	}}
	
	public ResponseEntity<?> getJournalforId(ObjectId id,String username){
		User user=userRepository.findByUserName(username);
		 Journal journal = user.getJournalEntries().stream()
		            .filter(entry -> entry.getId().equals(id))
		            .findFirst()
		            .orElse(null);
		if(journal != null){
			log.info("Inside getJournalforId, Journal entry: {} with ID {} ",journal,id);
		return new ResponseEntity<>(journal,HttpStatus.OK);
		}
			log.info("Inside getJournalforId, Journal entry not found for user: {} and ID: {}",username,id);
			return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
	}
	
	public  ResponseEntity<?> updateJournal(String username,ObjectId id,Journal journalNew){
		User user=userRepository.findByUserName(username);
		Journal journal = user.getJournalEntries().stream()
	            .filter(entry -> entry.getId().equals(id))
	            .findFirst()
	            .orElse(null);
		if(journal != null){
			journal.setTopic(journalNew.getTopic()!=null && !journalNew.getTopic().equals("")? journalNew.getTopic() : journal.getTopic());
			journal.setContent(journalNew.getContent()!=null && !journalNew.getContent().equals("")? journalNew.getContent() : journal.getContent());
			journalRepository.save(journal); 
			log.info("Inside updateJournal, Journal entry {} updated successfully for user: {}",journal,user);
			return new ResponseEntity<>(HttpStatus.OK);	
		}else
			log.info("Inside updateJournal, Journal entry not found for user: {} and ID: {}",username,id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
	}
	// @Transactional is required because if the delete operation fails in between then from both journal 
	// and user table data will not delete 
	// it will delete the entry for both or neither of one
	
	@Transactional
	public ResponseEntity<Journal> deleteJournal(String username,ObjectId id) {
	boolean removed=false;
		User user=userRepository.findByUserName(username);
		removed=user.getJournalEntries().removeIf(x->x.getId().equals(id));
		if(removed) {
		//No Cascade Delete by Default: MongoDB does not automatically delete the referenced documents 
			// in the other collection when you delete a document.
			// therfore we are deleting entries from both collections
		userRepository.save(user);
		journalRepository.deleteById(id);
		log.info("Inside deleteJournal, Journal entry {} deleted successfully for user: {}",id,user);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else
			log.info("Inside deleteJournal, Journal entry not found for user: {} and ID: {}",username,id);
			return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
