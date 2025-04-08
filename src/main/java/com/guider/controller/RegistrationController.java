package com.guider.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.entity.User;
import com.guider.repository.UserRepository;
import com.guider.services.UserService;

@RestController
@RequestMapping("/register")
public class RegistrationController {
 
	
	    private final UserService userService;

	    public RegistrationController( UserService userService ) {
	      
			this.userService = userService;
	    }
	   
	    @PostMapping("/users")
	    public ResponseEntity<Map<String, Object>>registerUser(@RequestBody User user) {
	    	user.setRole("ROLE_USER");

	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    @PostMapping("/interviewer")
	    public ResponseEntity<Map<String, Object>>  registerInterviewer(@RequestBody User user) {
	    	user.setRole("ROLE_INTERVIEWER");
	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    
	    @PostMapping("/hr")
	    public ResponseEntity<Map<String, Object>>  registerHr(@RequestBody User user) {
	    	user.setRole("ROLE_HR");
	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    
	   
	    
}
