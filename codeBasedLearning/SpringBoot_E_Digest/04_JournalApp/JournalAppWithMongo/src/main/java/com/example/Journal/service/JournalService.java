package com.example.Journal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.Journal.entity.Journal;
import com.example.Journal.exception.CustomApplicationException;
import com.example.Journal.repository.JournalRepository;

@Service
public class JournalService {

	@Autowired
	private JournalRepository journalRepository;
	
	public void saveEntry(Journal journal) {
		// save will update if id is already present but insert does not allows duplicates
		//journalRepository.save(journal);
		try {
		journalRepository.insert(journal);
		}catch (Exception e) {
			System.out.println("Duplicate key not allowed: "+e.getMessage());
			throw new CustomApplicationException("Duplicate Key not allowed: Id="+journal.getId(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}	
		
	}
	
	public List<Journal> getAll(){
		return journalRepository.findAll();
	}
	
	public Journal getJournalforId(String id){
		return journalRepository.findById(id).orElse(new Journal());
	}
	
	public  void updateJournal(String id,Journal journalNew){
		Journal journalOld=journalRepository.findById(id).orElse(null);
		if(journalOld!=null) {
			journalOld.setTopic(journalNew.getTopic()!=null && !journalNew.getTopic().equals("")? journalNew.getTopic() : journalOld.getTopic());
			journalOld.setContent(journalNew.getContent()!=null && !journalNew.getContent().equals("")? journalNew.getContent() : journalOld.getContent());
		}
			journalRepository.save(journalOld); 
	}
	
	public void deleteJournal(String id) {
		if(journalRepository.findById(id)!=null)
		journalRepository.deleteById(id);
	}
}
