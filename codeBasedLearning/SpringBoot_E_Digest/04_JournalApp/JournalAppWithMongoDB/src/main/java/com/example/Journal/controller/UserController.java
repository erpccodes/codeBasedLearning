package com.example.Journal.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Journal.entity.User;
import com.example.Journal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read, Update & Delete User")
public class UserController {
	
	@Autowired
	private UserService userService;
		        
	
	@PutMapping("/perform")
	@Operation(summary =  "Update the User details")
	public ResponseEntity<User> UpdateUser(@RequestBody User user) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();  //Fetching the object of currently authenticated user.
		String username=authentication.getName();
		return userService.updateUser(username,user);
	}
	
	@DeleteMapping("/perform")
	@Operation(summary =  "Delete a User")
	public ResponseEntity<?> deleteUser() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		return userService.deleteUser(username);
		
	}
	
	@GetMapping("/perform")
	@Operation(summary =  "Get loged in User Details")
	public ResponseEntity<?> GetLogedInUser(HttpServletRequest request){
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String username=authentication.getName();
		return userService.getUserByUserName(username,request);
	}
	
	
}
