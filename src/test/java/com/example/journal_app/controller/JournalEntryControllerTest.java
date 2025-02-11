package com.example.journal_app.controller;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.service.JournalEntryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JournalEntryController.class)
public class JournalEntryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JournalEntryService journalEntryService;

    @Test
    @WithMockUser // Simule un utilisateur authentifié
    public void whenCreateEntry_thenReturn200() throws Exception {
        // Création d'une entrée de test
        JournalEntry testEntry = new JournalEntry();
        testEntry.setTitle("Test Journal Entry");
        testEntry.setContent("This is a test content");

        // Configuration du mock du service
        when(journalEntryService.createEntry(any(JournalEntry.class)))
                .thenReturn(testEntry);

        // Conversion en JSON
        String entryJson = objectMapper.writeValueAsString(testEntry);

        // Exécution de la requête avec le jeton CSRF
        mockMvc.perform(post("/api/entries")
                        .with(SecurityMockMvcRequestPostProcessors.csrf()) // Ajoute le jeton CSRF
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(entryJson))
                .andExpect(status().isOk());
    }
}