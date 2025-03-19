package com.guider.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guider.entity.User;
import com.guider.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(User user) {
        // Check for existing user
        Optional<User> existingUser = userRepository.findByUsernameOrEmailOrPhoneNumber(
                user.getUsername(), user.getEmail(), user.getPhoneNumber()
        );

        if (existingUser.isPresent()) {
            return "Error: User with the same username, email, or phone number already exists!";
        }

        // Encrypt password and set role
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        userRepository.save(user);
        return "User registered successfully!";
    }
}

