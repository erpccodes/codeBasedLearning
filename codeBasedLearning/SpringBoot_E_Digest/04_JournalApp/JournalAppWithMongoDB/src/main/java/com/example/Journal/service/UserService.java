package com.example.Journal.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Journal.entity.User;
import com.example.Journal.exception.CustomApplicationException;
import com.example.Journal.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	
	public ResponseEntity<?> saveEntry(User user) {
		// save will update if id is already present but insert does not allows duplicates
		//journalRepository.save(journal);
		if((user.getUserName()!=null && !user.getPassword().isEmpty()) && (user.getPassword()!=null && !user.getPassword().isEmpty())) {
		try {
			userRepository.insert(user);
		return new  ResponseEntity<>(user,HttpStatus.CREATED);
		}catch (Exception e) {
			System.out.println("Duplicate key not allowed: "+e.getMessage());
			throw new CustomApplicationException("Duplicate Key not allowed: Id="+user.getId(),HttpStatus.CONFLICT);
			// TODO: handle exception
		}
		}
		return new ResponseEntity<>("username or password cannot be null or Blank",HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<User> getUserByUserName(String username){
		if(userRepository.findByUserName(username)!=null) {
		return new ResponseEntity<>(userRepository.findByUserName(username),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public  ResponseEntity<User> updateUser(String username,User user){
		User userInDb=userRepository.findByUserName(username);
		if(userInDb!=null) {
			userInDb.setUserName(user.getUserName());
			userInDb.setPassword(user.getPassword());
			userRepository.save(userInDb);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	public ResponseEntity<?> deleteUser(String username) {
		User user=userRepository.findByUserName(username);
		if(user!=null){
			userRepository.deleteByUserName(username);
		return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else
			return new  ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
