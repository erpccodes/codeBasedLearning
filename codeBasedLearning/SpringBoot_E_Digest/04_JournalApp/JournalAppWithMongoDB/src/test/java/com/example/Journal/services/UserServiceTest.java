package com.example.Journal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepository;
import com.example.Journal.service.UserService;

public class UserServiceTest {
	
	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	private PasswordEncoder encoder;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test.
        encoder = new BCryptPasswordEncoder(); // Initialize the password encoder.
    }


	@Test
	void testSaveEntry_Success() {
		
		// Arrange
		User mockuser=new User();
		mockuser.setId(new ObjectId());
		mockuser.setUserName("Test");
		mockuser.setPassword("TestPassword");
		mockuser.setRoles(Collections.singletonList("USER"));
		System.out.println(mockuser.toString());
		when(userRepository.insert(mockuser)).thenReturn(mockuser);
		
		//Act
		ResponseEntity<?> response=userService.saveEntry(mockuser);
		
		// Assert
		assertEquals(HttpStatus.CREATED,response.getStatusCode());  // Ensure HTTP status is CREATED.
		User savedUser=(User) response.getBody();
		assertNotNull(savedUser); // The saved user should not be null.
		assertTrue(encoder.matches("TestPassword", savedUser.getPassword())); // Ensure the password is encoded.
		verify(userRepository, times(1)).insert(any(User.class)); // Verify the insert method was called once.
		
	}
	
}
