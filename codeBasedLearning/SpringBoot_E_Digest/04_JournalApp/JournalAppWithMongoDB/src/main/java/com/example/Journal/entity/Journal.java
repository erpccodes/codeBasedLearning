package com.example.Journal.entity;

import java.time.LocalDateTime;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


// If the below annotation is not specified then table name will be class name is small case
@Document(collection = "myJournal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Journal {

	@Id
	private String id;
	private String Topic;
	private String content;
	private LocalDateTime date; 
	
}


