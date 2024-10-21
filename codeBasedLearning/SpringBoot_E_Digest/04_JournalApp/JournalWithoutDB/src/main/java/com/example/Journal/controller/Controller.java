package com.example.Journal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.Journal.entity.Journal;


@RestController
@RequestMapping("/journal")
public class Controller {
	
	Map<Long,Journal> journals=new HashMap<>();

	@GetMapping
	public List<Journal> GetJournals() {
		return new ArrayList<Journal>(journals.values());
	}
	
	@PostMapping
	public void PostJournal(@RequestBody Journal journal) {
		journals.put(journal.getId(), journal);
	}
	
	@PutMapping("/id/{id}")
	public void UpdateJournal(@PathVariable Long id,@RequestBody Journal journal) {
		journals.put(id, journal);
	}
	
	@DeleteMapping("/id/{id}")
	public void DeleteJournal(@PathVariable Long id) {
		journals.remove(id);
	}
	
	@GetMapping("/id/{id}")
	public Journal GetById(@PathVariable Long id){
		return journals.get(id);
	}
}
