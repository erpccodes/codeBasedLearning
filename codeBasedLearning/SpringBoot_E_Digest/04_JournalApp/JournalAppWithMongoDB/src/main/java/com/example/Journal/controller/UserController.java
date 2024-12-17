package com.example.Journal.controller;

import java.util.List;

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


import com.example.Journal.entity.User;
import com.example.Journal.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public List<User> GetJournals() {
		return userService.getAll();
	}
	
	@PostMapping
	public ResponseEntity<?> PostJournal(@RequestBody User user) {
		return userService.saveEntry(user);
	}
	
	@PutMapping("/{username}")
	public ResponseEntity<User> UpdateJournal(@PathVariable String username,@RequestBody User user) {
		return userService.updateUser(username,user);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable String username) {
		return userService.deleteUser(username);
		
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> GetById(@PathVariable String username){
		return userService.getUserByUserName(username);
	}
}
