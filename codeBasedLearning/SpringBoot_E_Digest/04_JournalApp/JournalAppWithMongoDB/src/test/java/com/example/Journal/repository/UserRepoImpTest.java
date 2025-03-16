package com.example.Journal.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mongodb.assertions.Assertions;

@SpringBootTest
public class UserRepoImpTest {

	@Autowired
	UserRepositoryImp repositoryImp;
	
	@Test
	public void getUserForSATest() {
		System.out.println(repositoryImp.getUserForSA());
		Assertions.assertFalse(repositoryImp.getUserForSA().isEmpty());
	}
}
