package com.guider.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guider.dto.Response;
import com.guider.entity.Interviewer;
import com.guider.entity.User;
import com.guider.repository.UserRepository;
import com.guider.services.InterviewerService;
import com.guider.services.UserService;
@RestController
@RequestMapping("/register")
public class RegistrationController {
 
	
	    private final UserService userService;
	    private final InterviewerService interviewerService;

	    public RegistrationController( UserService userService , InterviewerService interviewerService ) {
	      
			this.userService = userService;
			this.interviewerService =  interviewerService;
	    }
	   
	    @PostMapping("/users")
	    public ResponseEntity<Response> registerUser(@RequestBody User user) {
	    	user.setRole("ROLE_USER");

	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    @PostMapping("/interviewer")
	    public ResponseEntity<Map<String, Object>>  registerInterviewer(@RequestBody Interviewer interviewer) {
	    	 return interviewerService.saveInterviewer(interviewer);
	    	
	    	
	    }
	    
	    @PostMapping("/hr")
	    public ResponseEntity<Response>  registerHr(@RequestBody User user) {
	    	user.setRole("ROLE_HR");
	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    
	   
	    
}
