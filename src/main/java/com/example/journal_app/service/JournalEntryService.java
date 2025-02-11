package com.example.journal_app.service;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.model.User;
import com.example.journal_app.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JournalEntryService {
    private final JournalEntryRepository journalEntryRepository;

    public JournalEntry createEntry(JournalEntry entry) {
        // Validation de base
        if (entry.getContent() == null || entry.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Le contenu ne peut pas être vide");
        }

        // Ajout de la date de création si elle n'est pas déjà définie
        if (entry.getCreatedAt() == null) {
            entry.setCreatedAt(LocalDateTime.now());
        }

        return journalEntryRepository.save(entry);
    }


    public Optional<JournalEntry> getEntryById(Long id) {
        // Retourne un Optional comme attendu par votre contrôleur
        return journalEntryRepository.findById(id);
    }

    public void deleteEntry(Long id) {
        // Vérifie l'existence avant la suppression
        if (journalEntryRepository.existsById(id)) {
            journalEntryRepository.deleteById(id);
        }
        // Silencieux si l'entrée n'existe pas, comme dans votre contrôleur
    }
}