package com.example.Journal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Journal.entity.Journal;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

}
