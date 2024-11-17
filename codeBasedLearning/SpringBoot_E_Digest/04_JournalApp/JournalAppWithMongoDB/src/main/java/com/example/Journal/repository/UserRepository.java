package com.example.Journal.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Journal.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User,ObjectId>{
 User findByUserName(String username);
 void deleteByUserName(String username);
}
