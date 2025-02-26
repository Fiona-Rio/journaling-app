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
        testUser.setUsername("testuser2");
        testUser.setPassword("testpassword");
        testUser.setEmail("email2@email.com");
        User savedUser = userService.save(testUser);

        // Maintenant, utilisez l'ID généré automatiquement
        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setTitle("Aurais-je réussi ?");
        journalEntry.setContent("Quel ne fût pas ma surprise quand j'ai réalisé que mon journal avait été publié dans ma base de donnée. What a day !");
        journalEntry.setUser(savedUser);

        JournalEntry savedEntry = journalEntryService.save(journalEntry);

        // Vérifier que l'entrée a été sauvegardée
        assertNotNull(savedEntry.getId());
    }
}