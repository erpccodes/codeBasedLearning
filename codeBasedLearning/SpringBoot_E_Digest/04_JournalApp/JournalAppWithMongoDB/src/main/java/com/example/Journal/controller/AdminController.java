package com.example.Journal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepository;
import com.example.Journal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "Admin APIs", description = "APIs for admin User")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/admin/getUsers")
	@Operation(summary =  "To fetch all the Users in the system")
	public ResponseEntity<?> GetAllUsers() {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();  //Fetching the object of currently authenticated user.
		String authenticatedUser=authentication.getName();
		List<String> roles=userRepository.findByUserName(authenticatedUser).getRoles();
		if(roles.contains("ADMIN")) {
		return new ResponseEntity<>(userService.getAll(),HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>("Only Admin user can view all the Users",HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("/admin/add")
	@Operation(summary =  "To Add a admin User but only ADMIN user can Add new ADMIN USER")
	public ResponseEntity<?> CreateAdmin(@RequestBody 	
			@io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Login payload with user credentials",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            name = "Login Example",
                            summary = "A typical login request",
                            value = "{ \"userName\": \"String\", \"password\": \"String\" }"
                    )
            )
    ) User user) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();  //Fetching the object of currently authenticated user.
		String authenticatedUser=authentication.getName();
		List<String> roles=userRepository.findByUserName(authenticatedUser).getRoles();
		if(roles.contains("ADMIN")) {
		user.setRoles(java.util.Arrays.asList("ADMIN"));
		return userService.saveEntry(user);
		}
		else {
			return new ResponseEntity<>("Only Admin user can add new Admin",HttpStatus.UNAUTHORIZED);
		}
	}

}
