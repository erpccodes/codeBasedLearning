package com.example.Journal.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongodb.lang.NonNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

// Note we mark @Document in mongo but when we use JPA we Use @Entity
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
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@DBRef //annotation stores the reference (ObjectId) to the Journal document(s) in the parent document.
	private List<Journal> journalEntries= new ArrayList<>();
	private List<String> roles;
	@Override
	public String toString() {
		return "User: [id=" + id + ", userName=" + userName + ", password=" + password + ", journalEntries="
				+ journalEntries + ", roles=" + roles + "]";
	}
}
