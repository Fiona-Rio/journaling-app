package com.example.journal_app.repository;


import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    List<JournalEntry> findByUserOrderByCreatedAtDesc(User user);
}