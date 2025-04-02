package com.guider.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.guider.entity.Interviewer;
import com.guider.repository.InterviewerRepo;
import com.guider.repository.UserRepository;


@Service
public class InterviewerService {
	
	private final InterviewerRepo interviewerRepository;
    private final PasswordEncoder passwordEncoder;
    
    public InterviewerService(InterviewerRepo interviewerRepository, PasswordEncoder passwordEncoder) {
    	this.interviewerRepository = interviewerRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    
    public ResponseEntity<Map<String, Object>> registerInterviewer( Interviewer interviewer) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Check if interviewer already exists
            Optional<Interviewer> existingUser = interviewerRepository.findByUsernameOrEmailOrPhoneNumber(
                    interviewer.getUsername(), interviewer.getEmail(), interviewer.getPhoneNumber()
            );

            if (existingUser.isPresent()) {
                response.put("success", false);
                response.put("message", "Interviewer with the same username, email, or phone number already exists!");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            // Encrypt password and set role
            interviewer.setPassword(passwordEncoder.encode(interviewer.getPassword()));
            interviewer.setRole("ROLE_HR");

            // Save interviewer
            Interviewer savedInterviewer = interviewerRepository.save(interviewer);

            response.put("success", true);
            response.put("message", "Interviewer registered successfully!");
            response.put("interviewerId", savedInterviewer.getInterviewer_id()); // Returning interviewer ID

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An error occurred while registering the interviewer.");
            response.put("error", e.getMessage()); // Provide error details for debugging

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
