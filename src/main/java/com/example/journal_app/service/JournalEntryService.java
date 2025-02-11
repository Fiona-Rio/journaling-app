package com.example.journal_app.service;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.model.User;
import com.example.journal_app.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;

    public JournalEntry createEntry(JournalEntry entry) {
        return journalEntryRepository.save(entry);
    }

    public List<JournalEntry> getUserEntries(User user) {
        return journalEntryRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Optional<JournalEntry> getEntryById(Long id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteEntry(Long id) {
        journalEntryRepository.deleteById(id);
    }
}