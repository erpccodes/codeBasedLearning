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
			
			return new ResponseEntity<>(journal,HttpStatus.OK); 
		}catch(Exception e) {
			return new  ResponseEntity<>(HttpStatus.CONFLICT);
		}
		}	
		
	
	public ResponseEntity<?> getAllJournalEntriesOfUser(String username){
		User user=userRepository.findByUserName(username);
		List<Journal> journals=user.getJournalEntries();
		if(journals!=null && !journals.isEmpty())
			return new ResponseEntity<>(journals,HttpStatus.OK);
		else 
			return new ResponseEntity<>("No Journal for this User",HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<?> getJournalforId(ObjectId id,String username){
		User user=userRepository.findByUserName(username);
		 Journal journal = user.getJournalEntries().stream()
		            .filter(entry -> entry.getId().equals(id))
		            .findFirst()
		            .orElse(null);
		if(journal != null){
		return new ResponseEntity<>(journal,HttpStatus.OK);
		}
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
			return new ResponseEntity<>(HttpStatus.OK);	
		}else
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
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else
			return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
