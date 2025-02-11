package com.example.journal_app.controller;
// Le package doit correspondre à celui de la classe testée


// Imports nécessaires
import org.junit.jupiter.api.Test;  // Annotation @Test de JUnit 5
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;  // Pour les tests Spring Boot
import org.springframework.test.web.servlet.MockMvc;
// ... autres imports

// Déclaration de la classe de test
@SpringBootTest  // Cette annotation indique que c'est un test Spring Boot
@AutoConfigureMockMvc  // Pour tester les controllers
public class JournalEntryControllerTest {  // Par convention, le nom se termine par "Test"

    // Injection des dépendances nécessaires pour les tests
    @Autowired
    private MockMvc mockMvc;

    // Méthodes de test
    @Test  // Chaque méthode de test doit avoir cette annotation
    public void whenCreateEntry_thenReturn201() throws Exception {
        // Contenu du test
    }
}