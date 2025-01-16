package com.example.Journal.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Journal.entity.User;
import com.example.Journal.repository.UserRepository;
import com.example.Journal.service.UserDetailsServiceImpl;

public class UserDetailsServiceImpTests {

	
	  @Mock
	    private UserRepository userRepository;

	    @InjectMocks
	    private UserDetailsServiceImpl userDetailsService;

	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }

	    @Test
	    void loadUserByUsername_UserFound_ReturnsUserDetails() {
	        // Arrange
	        User mockUser = new User();
	        mockUser.setUserName("testUser");
	        mockUser.setPassword("password");
	        mockUser.setRoles(Collections.singletonList("USER"));

	        when(userRepository.findByUserName("testUser")).thenReturn(mockUser);

	        // Act
	        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

	        // Assert
	        assertNotNull(userDetails);
	        assertEquals("testUser", userDetails.getUsername());
	        assertEquals("password", userDetails.getPassword());
	        assertTrue(userDetails.getAuthorities().stream()
	                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")));
	        // ROLE_ is automatically appended by spring security

	        // To check the method was called and Exactly at once
	        verify(userRepository, times(1)).findByUserName("testUser");
	    }

	    @Test
	    void loadUserByUsername_UserNotFound_ThrowsException() {
	        // Arrange
	        when(userRepository.findByUserName("nonExistentUser")).thenReturn(null);

	        // Act & Assert
	        UsernameNotFoundException exception = assertThrows(
	                UsernameNotFoundException.class,
	                () -> userDetailsService.loadUserByUsername("nonExistentUser")
	        );

	        
	     // Assert
	        assertEquals("User Not Found with the username: nonExistentUser", exception.getMessage());
	        verify(userRepository, times(1)).findByUserName("nonExistentUser");
	    }
}
