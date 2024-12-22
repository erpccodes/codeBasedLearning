package com.example.Journal.controller;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Journal.entity.Journal;
import com.example.Journal.service.JournalService;


@RestController
@RequestMapping("/journal")
public class JournalController {
		
	@Autowired
	private JournalService journalService;
	
	@GetMapping()
	public ResponseEntity<?> GetJournalsOfUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
		String username=authentication.getName();
		return journalService.getAllJournalEntriesOfUser(username);
	}
	
	@PostMapping()
	public ResponseEntity<Journal> PostJournal(@RequestBody Journal journal) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
		String username=authentication.getName();
		return journalService.saveJournalEntryOfUser(journal,username);
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<?> UpdateJournal(@PathVariable String id,@RequestBody Journal journal) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
		String username=authentication.getName();
		// Handling case when we send wrong id in path variable then spring tries to convert this string to mongo objectid 
		//and fails as it does not match the expected format of objectId so replace the datatype to string in path variable
		// and the below check is added
		 if (!ObjectId.isValid(id)) {
		        return new ResponseEntity<>("Journal Id is invalid", HttpStatus.BAD_REQUEST); 
		    }

		    ObjectId objectId = new ObjectId(id);
		return journalService.updateJournal(username,objectId,journal);
		
	}
	 
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> DeleteJournalforUser(@PathVariable String id) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
		String username=authentication.getName();
		 if (!ObjectId.isValid(id)) {
		        return new ResponseEntity<>("Journal Id is invalid", HttpStatus.BAD_REQUEST);
		    }

		    ObjectId objectId = new ObjectId(id);
		return journalService.deleteJournal(username,objectId);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<?> GetById(@PathVariable String id){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication(); 
		String username=authentication.getName();
		if (!ObjectId.isValid(id)) {
	        return new ResponseEntity<>("Journal Id is invalid", HttpStatus.BAD_REQUEST);
	    }

	    ObjectId objectId = new ObjectId(id);
		return journalService.getJournalforId(objectId,username);
	}
}
