package com.example.journal_app.controller;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    // CREATE
    @PostMapping("/entries")
    public JournalEntry saveEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.save(journalEntry);
    }

    // READ - All entries
    @GetMapping("/entries")
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }

    // READ - Single entry by ID
    @GetMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long id) {
        JournalEntry entry = journalEntryService.getJournalEntryById(id);
        if (entry != null) {
            return ResponseEntity.ok(entry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // UPDATE
    @PutMapping("/entries/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable Long id, @RequestBody JournalEntry journalEntry) {
        journalEntry.setId(id); // Assurez-vous que l'ID dans le chemin corresponde à l'objet
        JournalEntry updatedEntry = journalEntryService.update(journalEntry);
        if (updatedEntry != null) {
            return ResponseEntity.ok(updatedEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE
    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        journalEntryService.delete(id);
        return ResponseEntity.ok().build();
    }
}