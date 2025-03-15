package com.guider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guider.entity.AuthRequest;
import com.guider.entity.User;
import com.guider.services.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/auth")
public class CheckController {
	 
	 private final AuthenticationManager authenticationManager;
	 private final JwtUtil jwtUtil;
	 private final PasswordEncoder passwordEncoder;

	    public CheckController(AuthenticationManager authenticationManager , JwtUtil jwtUtil , PasswordEncoder passwordEncoder) {
	        this.authenticationManager = authenticationManager;
	        this.jwtUtil = jwtUtil;
	        this.passwordEncoder =  passwordEncoder;
	    }
	
    @PostMapping("/check")
	public String checkMethod(@RequestBody User user) {
    	String token = jwtUtil.generateToken(user);
    	System.out.println("Generated Token: " + token);
		return "security working fine";
	}
    
    @PostMapping("/tokenValidation")
    public String checkToken() {
		return "checking token";
    	
    }
    
    
    @PostMapping("/customlogin")
	public String login(@RequestBody User authRequest) {
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
    	System.out.println(authentication);
    	if (authentication.isAuthenticated()) {
    		return jwtUtil.generateToken(authRequest);
    	}
    	else {
            throw new RuntimeException("Invalid username or password");
        }
    	
	}
	

}
