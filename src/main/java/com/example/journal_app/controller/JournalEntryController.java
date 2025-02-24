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

    @GetMapping("/entries")
    public List<JournalEntry> getAllJournalEntries() {
        return journalEntryService.getAllJournalEntries();
    }

    @PostMapping("/entries")
    public JournalEntry saveEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.save(journalEntry);
    }

    @DeleteMapping("/entries/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        journalEntryService.delete(id);
        return ResponseEntity.ok().build();
    }
}