package com.example.Journal.dto;

import java.util.List;

import com.example.Journal.entity.Journal;

import lombok.Data;

@Data
public class UserDTO {
	private String id;
    private String userName;
    private List<Journal> journalEntries;  
    private List<String> roles;

    @Override
	public String toString() {
		return "User: [id=" + id + ", userName=" + userName + ", journalEntries=" + journalEntries + ", roles="
				+ roles + "]";
	}
}
