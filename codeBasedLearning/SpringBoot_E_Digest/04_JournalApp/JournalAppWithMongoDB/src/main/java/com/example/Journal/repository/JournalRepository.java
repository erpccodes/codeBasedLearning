package com.example.Journal.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Journal.entity.Journal;

@Repository
public interface JournalRepository extends MongoRepository<Journal, String> {
}
