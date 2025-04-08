//package com.guider.controller;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.guider.entity.AuthRequest;
//import com.guider.entity.User;
//import com.guider.services.JwtUtil;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app
//@RestController
//@RequestMapping("/auth")
//public class CheckController {
//	 
//	 private final AuthenticationManager authenticationManager;
//	 private final JwtUtil jwtUtil;
//	 private final PasswordEncoder passwordEncoder;
//
//	    public CheckController(AuthenticationManager authenticationManager , JwtUtil jwtUtil , PasswordEncoder passwordEncoder) {
//	        this.authenticationManager = authenticationManager;
//	        this.jwtUtil = jwtUtil;
//	        this.passwordEncoder =  passwordEncoder;
//	    }
//	
//    @PostMapping("/check")
//	public String checkMethod(@RequestBody User user) {
//    	String token = jwtUtil.generateToken(user);
//    	System.out.println("Generated Token: " + token);
//		return "security working fine";
//	}
//    
//   
//    
//    
//    @PostMapping("/customlogin")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody User authRequest) {
//        Map<String, Object> response = new HashMap<>();
//        try {
//            // Authenticate user
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
//            );
//
//            // Check if authentication is successful
//            if (authentication.isAuthenticated()) {
//                String token = jwtUtil.generateToken(authRequest);
//                response.put("success", true);
//                response.put("message", "Login successful!");
//                response.put("token", token);
//
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("success", false);
//                response.put("message", "Invalid username or password");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        } catch (Exception e) {
//            response.put("success", false);
//            response.put("message", "Authentication failed!");
//            response.put("error", e.getMessage());
//
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }
//	
//
//}
