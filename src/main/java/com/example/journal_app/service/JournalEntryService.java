package com.example.journal_app.service;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository repository;

    public JournalEntry save(JournalEntry entry) {
        // You might want to add validation or preprocessing here
        if (entry.getCreatedAt() == null) {
            entry.setCreatedAt(LocalDateTime.now());
        }
        return repository.save(entry);
    }

    public List<JournalEntry> getAllJournalEntries() {
        return repository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}