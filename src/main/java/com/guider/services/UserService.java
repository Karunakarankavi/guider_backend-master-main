package com.guider.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guider.dto.Response;
import com.guider.entity.User;
import com.guider.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Response> registerUser(User user) {
        try {
            Optional<User> existingUser = userRepository.findByUsernameOrEmailOrPhoneNumber(
                user.getUsername(), user.getEmail(), user.getPhoneNumber()
            );

            if (existingUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new Response(false, "User with the same username, email, or phone number already exists!", null, null)
                );
            }

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User savedUser = userRepository.save(user);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new Response(true, "User registered successfully!", savedUser.getUser_id(), null)
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new Response(false, "An error occurred while registering the user.", null, e.getMessage())
            );
        }
    }
}

