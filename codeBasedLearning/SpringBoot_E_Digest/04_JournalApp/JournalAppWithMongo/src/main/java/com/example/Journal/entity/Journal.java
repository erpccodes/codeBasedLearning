package com.example.Journal.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


// If the below annotation is not specified then table name will be class name is small case
@Document(collection = "myJournal")
public class Journal {

	@Id
	private String id;
	private String Topic;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return Topic;
	}
	public void setTopic(String topic) {
		Topic = topic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}


