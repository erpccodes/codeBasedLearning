package com.example.Journal.controller;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/{username}")
	public ResponseEntity<?> GetJournalsOfUser(@PathVariable String username) {
		return journalService.getAllJournalEntriesOfUser(username);
	}
	
	@PostMapping("/{username}")
	public ResponseEntity<Journal> PostJournal(@PathVariable String username,@RequestBody Journal journal) {
		return journalService.saveJournalEntryOfUser(journal,username);
	}
	
	@PutMapping("/id/{username}/{id}")
	public void UpdateJournal(@PathVariable String username,@PathVariable ObjectId id,@RequestBody Journal journal) {
		journalService.updateJournal(username,id,journal);
		
	}
	 
	@DeleteMapping("/id/{username}/{id}")
	public ResponseEntity<Journal> DeleteJournalforUser(@PathVariable String username,@PathVariable ObjectId id) {
		return journalService.deleteJournal(username,id);
		
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Journal> GetById(@PathVariable ObjectId id){
		return journalService.getJournalforId(id);
	}
}
