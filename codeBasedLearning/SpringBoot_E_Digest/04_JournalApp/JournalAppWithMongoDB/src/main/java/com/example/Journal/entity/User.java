package com.example.Journal.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

// Note we mark @Document but when we use JPA we Use @Entity
@Document(collection = "users")
@Data
@NoArgsConstructor

public class User {
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String userName;
	@NonNull
	private String password;
	@DBRef //annotation stores the reference (ObjectId) to the Journal document(s) in the parent document.
	private List<Journal> journalEntries= new ArrayList<>();
}