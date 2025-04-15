package com.guider.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.guider.entity.User;
import com.guider.services.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthController {
	 
	 private final AuthenticationManager authenticationManager;
	 private final JwtUtil jwtUtil;
	 private final PasswordEncoder passwordEncoder;

	    public AuthController(AuthenticationManager authenticationManager , JwtUtil jwtUtil , PasswordEncoder passwordEncoder) {
	        this.authenticationManager = authenticationManager;
	        this.jwtUtil = jwtUtil;
	        this.passwordEncoder =  passwordEncoder;
	    }
	
	    @PostMapping("/oauthLogin")
	    public ResponseEntity<Map<String, Object>> handleOAuthLogin(@RequestParam String role, HttpServletRequest request) {
	        System.out.println("Received role: " + role);
	        
	        // Store role in session
	        request.getSession().setAttribute("oauth2_role", role);
	        
	        // Redirect to Google OAuth2 endpoint
	        
	        Map<String, Object> response = new HashMap<>();
	        
	        response.put("redirecturl", "oauth2/authorization/google");
	        
            return ResponseEntity.ok(response);


	    }
    
   
    
    
    @PostMapping("/customlogin")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User authRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );

            // Check if authentication is successful
            if (authentication.isAuthenticated()) {
            	System.out.println("sucessful");
                String token = jwtUtil.generateToken(authRequest);
                response.put("success", true);
                response.put("message", "Login successful!");
                response.put("token", token);

                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Invalid username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } catch (Exception e) {
        	System.out.println("not");
        	System.out.println();
            response.put("success", false);
            response.put("message", "Authentication failed!");
            response.put("error", e.getMessage());

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
	

}

