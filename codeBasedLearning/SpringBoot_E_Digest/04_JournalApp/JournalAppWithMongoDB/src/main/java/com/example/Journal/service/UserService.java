package com.example.Journal.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Journal.dto.UserDTO;
import com.example.Journal.entity.User;
import com.example.Journal.entity.WeatherResponse;
import com.example.Journal.exception.CustomApplicationException;
import com.example.Journal.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder encoder=new BCryptPasswordEncoder();
	
	@Autowired
	WeatherService weatherService;
	
	
	

	public List<UserDTO> getAll() {
		// TODO Auto-generated method stub
		List<User> user = userRepository.findAll();
		List<UserDTO> userDTOs = user.stream().map(this::mapToDTO).collect(Collectors.toList());
		log.info("Get ALL call, Users list: " + userDTOs);
		return userDTOs;
	}

	
	public ResponseEntity<?> saveEntry(User user) {
		// save will update if id is already present but insert does not allows duplicates
		//journalRepository.save(journal);
		if((user.getUserName()!=null && !user.getPassword().isEmpty()) && (user.getPassword()!=null && !user.getPassword().isEmpty())) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			if(user.getRoles()==null)
				user.setRoles(Arrays.asList("USER"));
			userRepository.insert(user);
			log.info("inside save Entry, User saved sucessfully with Username:  "+user.getUserName());
			UserDTO userDTO = mapToDTO(user);
		return new  ResponseEntity<>(userDTO,HttpStatus.CREATED);
		}catch (Exception e) {
			log.error("inside save Entry, Duplicate key not allowed: "+e.getMessage());
			throw new CustomApplicationException("Duplicate Key not allowed: Id="+user.getId(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
		}
		return new ResponseEntity<>("username or password cannot be null or Blank",HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<?> getUserByUserName(String username,HttpServletRequest request){
		log.info("Get User By Username call, User: " + username);
		UserDTO userDTO=mapToDTO(userRepository.findByUserName(username));
		WeatherResponse weatherResponse=weatherService.getCurrentWeather(request);
		String finalWeatherResponse="";
		if(weatherResponse==null) {
			finalWeatherResponse="";
			}
		else {
            finalWeatherResponse="\nTodays Weather :"+weatherResponse+"\n";
        }
		return new ResponseEntity<>("Hi "+username+finalWeatherResponse+ userDTO,HttpStatus.OK);
	}
	
	public  ResponseEntity<User> updateUser(String username,User user){
		User userInDb=userRepository.findByUserName(username);
			userInDb.setUserName(user.getUserName());		
			userInDb.setPassword(encoder.encode(user.getPassword()));
			userRepository.save(userInDb);
			log.info("inside updateUser, User updated sucessfully with Username:  "+userInDb.getUserName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<?> deleteUser(String username) {
			userRepository.deleteByUserName(username);
			log.info("inside deleteUser, User deleted sucessfully with Username:  "+username);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	public UserDTO mapToDTO(User user) {
	    UserDTO dto = new UserDTO();
	    dto.setId(user.getId() != null ? user.getId().toString() : null);
	    dto.setUserName(user.getUserName());
	    dto.setJournalEntries(user.getJournalEntries());
	    dto.setRoles(user.getRoles());
	    return dto;
	}
}
