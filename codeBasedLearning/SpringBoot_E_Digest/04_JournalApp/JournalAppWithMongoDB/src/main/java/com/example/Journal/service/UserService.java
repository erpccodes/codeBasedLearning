package com.example.Journal.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Journal.entity.User;
import com.example.Journal.exception.CustomApplicationException;
import com.example.Journal.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	private final PasswordEncoder encoder=new BCryptPasswordEncoder();
	
	
	

	public List<User> getAll() {
		// TODO Auto-generated method stub
		log.info("Get ALL call, Users list: " + userRepository.findAll());
		return userRepository.findAll();
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
		return new  ResponseEntity<>(user,HttpStatus.CREATED);
		}catch (Exception e) {
			log.error("inside save Entry, Duplicate key not allowed: "+e.getMessage());
			throw new CustomApplicationException("Duplicate Key not allowed: Id="+user.getId(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
		}
		return new ResponseEntity<>("username or password cannot be null or Blank",HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<User> getUserByUserName(String username){
		log.info("Get User By Username call, User: " + username);
		return new ResponseEntity<>(userRepository.findByUserName(username),HttpStatus.OK);
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
}
