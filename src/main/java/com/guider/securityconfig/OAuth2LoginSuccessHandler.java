package com.guider.securityconfig;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.guider.entity.User;
import com.guider.repository.UserRepository;
import com.guider.services.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler { // ✅ Ensure correct interface implementation

    private final JwtUtil jwtUtil;
	 private final UserRepository userRepository;


    public OAuth2LoginSuccessHandler(JwtUtil jwtUtil , UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        // Extract user details
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        
        System.out.println("name" + name);
        System.out.println("email" + email);
        
        String role = (String) request.getSession().getAttribute("oauth2_role");
         

        Optional<User> existingUser = userRepository.findByEmailOrPhoneNumber( email , "");
        // Create User entity
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        if (existingUser.isEmpty()) {
           
        	System.out.println(role);
            user.setPassword("OAUTH_USER");
            user.setRole("ROLE_" + role);

            userRepository.save(user);
        } else {
            System.out.println("User already exists. Skipping save.");
        }


        // Generate JWT Token
        String token = jwtUtil.generateToken(user);

        // Prepare JSON response
        Map<String, String> responseData = new HashMap<>();
        responseData.put("token", token);
        responseData.put("message", "Login successful!");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String redirectUrl = "http://localhost:4200/dashboard?token=" + URLEncoder.encode( token , StandardCharsets.UTF_8);
        response.sendRedirect(redirectUrl);
    }
}
