package com.example.journal_app.controller;

import com.example.journal_app.model.JournalEntry;
import com.example.journal_app.model.User;
import com.example.journal_app.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@RequiredArgsConstructor
public class JournalEntryController {
    private final JournalEntryService journalEntryService;

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry entry) {
        return ResponseEntity.ok(journalEntryService.createEntry(entry));
    }


    @GetMapping("/{id}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable Long id) {
        return journalEntryService.getEntryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        journalEntryService.deleteEntry(id);
        return ResponseEntity.ok().build();
    }
}