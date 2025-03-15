package com.guider.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.entity.User;
import com.guider.repository.UserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {
 
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;

	    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	    }
	   
	    @PostMapping("/users")
	    public String registerUser(@RequestBody User user) {
	    	System.out.println("user called");
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	        user.setRole("ROLE_USER");
	        userRepository.save(user);
	        return "User registered successfully!";
	    	
	    	
	    }
	    
}
