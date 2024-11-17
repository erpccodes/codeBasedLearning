package com.example.Journal.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;


// If the below annotation is not specified then table name will be class name is small case
@Document(collection = "myJournal")
// data annotation are possible using Lombok lib
@Data
@NoArgsConstructor

public class Journal {

	@Id
	private ObjectId id;
	private String Topic;
	private String content;
	private LocalDateTime date; 
	
}


