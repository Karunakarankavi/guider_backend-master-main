package com.guider.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	    public RegistrationController( UserService userService , InterviewerService interviewerService) {
	      
			this.userService = userService;
			this.interviewerService =  interviewerService;
	    }
	   
	    @PostMapping("/users")
	    public String registerUser(@RequestBody User user) {
	    	
	    	 return userService.registerUser(user);
	    	
	    	
	    }
	    @PostMapping("/interviewer")
	    public String registeInterviewer(@RequestBody Interviewer interviewer) {
	    	
	    	 return interviewerService.registerInterviewer(interviewer);
	    	
	    	
	    }
	    
}
