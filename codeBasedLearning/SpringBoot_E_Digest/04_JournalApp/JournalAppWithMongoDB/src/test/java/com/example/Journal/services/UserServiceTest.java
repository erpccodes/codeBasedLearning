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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.Journal.dto.UserDTO;
import com.example.Journal.entity.User;
import com.example.Journal.entity.WeatherResponse;
import com.example.Journal.entity.WeatherResponse.Main;
import com.example.Journal.repository.UserRepository;
import com.example.Journal.service.UserService;
import com.example.Journal.service.WeatherService;

public class UserServiceTest {
	
	@InjectMocks //Use @InjectMocks to inject those mocks(@mock dependencies) into the class you're testing(user service).
	private UserService userService;
	
	@Mock
	private WeatherService weatherService;
	
	@Mock // Use @Mock for mocking the external dependencies, such as repositories, external services, or utilities.
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
		UserDTO savedUser= (UserDTO) response.getBody();
		assertNotNull(savedUser); // The saved user should not be null.
	//	assertTrue(encoder.matches("TestPassword", savedUser.getPassword())); // Ensure the password is encoded.
		
		 // Verify interaction
		verify(userRepository, times(1)).insert(any(User.class)); // Verify the insert method was called once.
		
	}
	
	@Test
	void getAllUsers() {
		
		// Arrange
		User mockuser=new User();
		mockuser.setId(new ObjectId());
		mockuser.setUserName("Test");
		mockuser.setPassword("TestPassword");
		mockuser.setRoles(Collections.singletonList("USER"));
		
		// Arrange
		User mockuser2=new User();
		mockuser2.setId(new ObjectId());
		mockuser2.setUserName("Test2");
		mockuser2.setPassword("TestPassword2");
		mockuser2.setRoles(Collections.singletonList("USER"));
		
		when(userRepository.findAll()).thenReturn(List.of(mockuser,mockuser2));
		
		//Act
		List<User> users=userRepository.findAll();
		
		// Assert
		assertEquals(users,List.of(mockuser,mockuser2));  // Ensure HTTP status is CREATED.
		assertNotNull(users); 
		 assertEquals(2, users.size());
		
		 // Verify interaction
		verify(userRepository, times(1)).findAll(); // Verify the insert method was called once.
		
	}
	
	@Test
    void testGetUserByUserName() {
        // Arrange: Set up the mock behavior for findByUserName.
        User user = new User();
        user.setId(null);
        user.setUserName("user1");
        user.setPassword("password");
        user.setRoles(Collections.singletonList("USER"));
        UserDTO userDTO = userService.mapToDTO(user);
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("127.0.0.1"); // Local IP for testing
        
        WeatherResponse weatherResponse = new WeatherResponse();
        Main main=new Main();
        main.setTemptemperature(25);
        main.setFeelsLike(27);
        weatherResponse.setMain(main);
        weatherResponse.setCity("Dadri");
        

        when(userRepository.findByUserName("user1")).thenReturn(user); // Mock the findByUserName method.
        
       when(weatherService.getCurrentWeather(request)).thenReturn(weatherResponse);

        // Act: Call the getUserByUserName method.
        ResponseEntity<?> response = userService.getUserByUserName("user1",request);

        // Assert: Verify the response.
        assertEquals(HttpStatus.OK, response.getStatusCode()); // Ensure HTTP status is OK.
        String expectedResponse = "Hi " + user.getUserName() + "\nTodays Weather :" + weatherResponse + "\n" + userService.mapToDTO(user);
        assertEquals(expectedResponse, response.getBody()); // Validate the returned user.
        verify(userRepository, times(1)).findByUserName("user1"); // Ensure the findByUserName method was called once.
    }
	
	 @Test
	    void testUpdateUser() {
	        // Arrange: Mock the existing user and updated user data.
	        User existingUser = new User();
	        existingUser.setId(null);
	        existingUser.setUserName("user1");
	        existingUser.setPassword("oldPassword");
	        existingUser.setRoles(Collections.singletonList("USER"));

	        User updatedUser = new User();
	        updatedUser.setId(null);
	        updatedUser.setUserName("newUser");
	        updatedUser.setPassword("newPassword");
	        updatedUser.setRoles(Collections.singletonList("USER"));

	        when(userRepository.findByUserName("user1")).thenReturn(existingUser); // Mock the findByUserName method.
	        when(userRepository.save(any(User.class))).thenReturn(existingUser); // Mock the save method.

	        // Act: Call the updateUser method.
	        ResponseEntity<User> response = userService.updateUser("user1", updatedUser);

	        // Assert: Validate the response.
	        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Ensure HTTP status is NO_CONTENT.
	        verify(userRepository, times(1)).findByUserName("user1"); // Verify findByUserName was called.
	        verify(userRepository, times(1)).save(any(User.class)); // Verify the save method was called.
	    }
	 
	 @Test
	    void testDeleteUser() {
	        // Act: Call the deleteUser method.
	        ResponseEntity<?> response = userService.deleteUser("user1");

	        // Assert: Validate the response.
	        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); // Ensure HTTP status is NO_CONTENT.
	        verify(userRepository, times(1)).deleteByUserName("user1"); // Verify the deleteByUserName method was called once.
	    }
	
}
