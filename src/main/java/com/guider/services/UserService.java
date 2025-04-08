package com.guider.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Map<String, Object>>  registerUser(User user) {
        Map<String, Object> response = new HashMap<>();

    	 try {
             // Check for existing user
             Optional<User> existingUser = userRepository.findByUsernameOrEmailOrPhoneNumber(
                  user.getUsername() ,    user.getEmail() , user.getPhoneNumber()
             );

             if (existingUser.isPresent()) {
                 response.put("success", false);
                 response.put("message", "User with the same username, email, or phone number already exists!");
                 return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
             }

             // Encrypt password and set role
             user.setPassword(passwordEncoder.encode(user.getPassword()));
             // Save the user
             User savedUser = userRepository.save(user);

             response.put("success", true);
             response.put("message", "User registered successfully!");
             response.put("userId", savedUser.getUser_id()); // Returning the saved user ID

             return ResponseEntity.status(HttpStatus.CREATED).body(response);

         } catch (Exception e) {
             response.put("success", false);
             response.put("message", "An error occurred while registering the user.");
             response.put("error", e.getMessage()); // Provide detailed error message for debugging

             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
         }
    }
}

