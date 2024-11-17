package com.example.Journal.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Journal.entity.Journal;
import com.example.Journal.entity.User;
import com.example.Journal.exception.CustomApplicationException;
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
		if(journals!=null || !journals.isEmpty())
			return new ResponseEntity<>(journals,HttpStatus.OK);
		else 
			return new ResponseEntity<>("No Journal for this User",HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<Journal> getJournalforId(ObjectId id){
		Optional<Journal> journal=journalRepository.findById(id);
		if(journalRepository.findById(id).isPresent()) {
		return new ResponseEntity<>(journalRepository.findById(id).get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public  ResponseEntity<?> updateJournal(String username,ObjectId id,Journal journalNew){
		Journal journalOld=journalRepository.findById(id).orElse(null);
		if(journalOld!=null) {
			journalOld.setTopic(journalNew.getTopic()!=null && !journalNew.getTopic().equals("")? journalNew.getTopic() : journalOld.getTopic());
			journalOld.setContent(journalNew.getContent()!=null && !journalNew.getContent().equals("")? journalNew.getContent() : journalOld.getContent());
			journalRepository.save(journalOld); 
			return new ResponseEntity<>(HttpStatus.OK);
		}else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
	}
	
	public ResponseEntity<Journal> deleteJournal(String username,ObjectId id) {
		if(journalRepository.findById(id)!=null) {
		journalRepository.deleteById(id);
		User user=userRepository.findByUserName(username);
		user.getJournalEntries().removeIf(x->x.getId().equals(id));
		userRepository.save(user);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else
			return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
