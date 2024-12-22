package com.example.Journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/admin/getUsers")
	public List<User> GetAllUsers() {
		return userService.getAll();
	}
	
	@PostMapping("/admin/add")
	public ResponseEntity<?> CreateAdmin(@RequestBody User user) {
		return userService.saveEntry(user);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> PostJournal(@RequestBody User user) {
		return userService.saveEntry(user);
	}
	
	@PutMapping("/perform")
	public ResponseEntity<User> UpdateJournal(@RequestBody User user) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();  //Fetching the object of currently authenticated user.
		String username=authentication.getName();
		return userService.updateUser(username,user);
	}
	
	@DeleteMapping("/perform")
	public ResponseEntity<?> deleteUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		return userService.deleteUser(username);
		
	}
	
	@GetMapping("/perform")
	public ResponseEntity<User> GetById(){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		return userService.getUserByUserName(username);
	}
}
