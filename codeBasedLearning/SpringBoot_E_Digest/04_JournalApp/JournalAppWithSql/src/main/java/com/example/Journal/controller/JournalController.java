package com.example.Journal.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping
	public List<Journal> GetJournals() {
		return journalService.getAll();
	}
	
	@PostMapping
	public void PostJournal(@RequestBody Journal journal) {
		journalService.saveEntry(journal);
	}
	
	@PutMapping("/id/{id}")
	public void UpdateJournal(@PathVariable Long id,@RequestBody Journal journal) {
		journalService.updateJournal(id,journal);
		
	}
	
	@DeleteMapping("/id/{id}")
	public void DeleteJournal(@PathVariable Long id) {
		journalService.deleteJournal(id);
		
	}
	
	@GetMapping("/id/{id}")
	public Journal GetById(@PathVariable Long id){
		return journalService.getJournalforId(id);
	}
}
