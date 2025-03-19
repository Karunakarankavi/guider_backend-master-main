package com.guider.services;

import java.util.Optional;

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
    
    public String registerInterviewer(Interviewer interviewer) {
        // Check for existing user
        Optional<Interviewer> existingUser = interviewerRepository.findByUsernameOrEmailOrPhoneNumber(
        		interviewer.getUsername(), interviewer.getEmail(), interviewer.getPhoneNumber()
        );

        if (existingUser.isPresent()) {
            return "Error: Interviwer with the same username, email, or phone number already exists!";
        }

        // Encrypt password and set role
        interviewer.setPassword(passwordEncoder.encode(interviewer.getPassword()));
        interviewer.setRole("ROLE_USER");

        interviewerRepository.save(interviewer);
        return "Interviewer registered successfully!";
    }

}
