package com.example.journal_app.controller;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.model.User;
import com.example.journal_app.service.JournalEntryService;
import com.example.journal_app.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JournalApplicationTest {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @Test
    void addJournalEntryTest() {
        // Créer et sauvegarder un utilisateur réel dans la base de données
        User testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPassword("password");
        testUser.setEmail("email@email.com");
        User savedUser = userService.save(testUser);

        // Maintenant, utilisez l'ID généré automatiquement
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle("Test d'entrée d'un journal");
        journalEntry.setContent("Ceci est un test pour créer une entrée de journal. J'espère que cela va marcher ! Bisou");
        journalEntry.setUser(savedUser);

        JournalEntry savedEntry = journalEntryService.save(journalEntry);

        // Vérifier que l'entrée a été sauvegardée
        assertNotNull(savedEntry.getId());
    }
}