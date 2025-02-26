package com.example.journal_app.service;

import com.example.journal_app.model.User;
import com.example.journal_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CREATE
    public User save(User user) {
        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // READ - Get current user
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return (User) userRepository.findByEmail(email).orElse(null);
    }

    // READ - Get user by ID
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // UPDATE
    public User update(User user) {
        // Vérifier si l'utilisateur existe
        if (!userRepository.existsById(user.getId())) {
            return null;
        }

        // Récupérer l'utilisateur existant pour conserver le mot de passe
        User existingUser = userRepository.findById(user.getId()).get();

        // Conserver le mot de passe encodé (ne pas le mettre à jour via cette méthode)
        user.setPassword(existingUser.getPassword());

        return userRepository.save(user);
    }

    // UPDATE - Change password
    public boolean changePassword(Long userId, String currentPassword, String newPassword) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            return false;
        }

        User user = userOpt.get();

        // Vérifier si le mot de passe actuel est correct
        if (passwordEncoder.matches(currentPassword, user.getPassword())) {
            // Encoder et sauvegarder le nouveau mot de passe
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }

        return false;
    }

    // DELETE (soft delete - deactivate account)
    public void deactivate(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }
}