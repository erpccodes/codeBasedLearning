package com.example.Journal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Journal.dto.UsersDTO;
import com.example.Journal.entity.User;
import com.example.Journal.service.UserDetailsServiceImpl;
import com.example.Journal.service.UserService;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import com.example.Journal.utils.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
@Tag(name = "Public APIs", description = "APIs having Login/Signup")
public class PublicController {
	
	@Autowired
	private UserService userService;
	
	 @Autowired
	    private AuthenticationManager authenticationManager;
	    
	 @Autowired   
	 private UserDetailsServiceImpl userDetailsService;
	    
	    
	    @Autowired
	    private JwtUtil jwtUtil;

	
	@PostMapping("/login")

	public ResponseEntity<?> login(
			@RequestBody
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
    ) User user){
		
		try{
			// this line is reposible for authenticating the user by comparing raw pass with encrypted 
			// password inside DB, Also if cred are correct the code follows the flow otherwise exception
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
	
	@PostMapping("/signup")
	public ResponseEntity<?> Signup(@RequestBody 
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
		    )UsersDTO user) {
		User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setUserName(user.getUserName());
        newUser.setPassword(user.getPassword());
        newUser.setSentimentAnalysis(user.isSentimentAnalysis());
        newUser.setRoles(java.util.Arrays.asList("USER"));
		return userService.saveEntry(newUser);
	}
}
