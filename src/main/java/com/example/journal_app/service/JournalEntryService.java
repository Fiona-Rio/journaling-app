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

    public JournalEntry getJournalEntryById(Long id) {
        Optional<JournalEntry> entryOptional = repository.findById(id);
        return entryOptional.orElse(null);
    }

    public JournalEntry update(JournalEntry journalEntry) {
        // Vérifier si l'entrée existe
        if (!repository.existsById(journalEntry.getId())) {
            return null;
        }

        // Préserver la date de création originale si elle existe déjà
        Optional<JournalEntry> existingEntry = repository.findById(journalEntry.getId());
        if (existingEntry.isPresent()) {
            LocalDateTime originalCreatedAt = existingEntry.get().getCreatedAt();
            journalEntry.setCreatedAt(originalCreatedAt);
        }

        // Mettre à jour la date de modification
        journalEntry.setUpdatedAt(LocalDateTime.now());

        return repository.save(journalEntry);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}