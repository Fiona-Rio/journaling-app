package com.example.journal_app.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    // Relation avec JournalEntry
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<JournalEntry> entries;
}