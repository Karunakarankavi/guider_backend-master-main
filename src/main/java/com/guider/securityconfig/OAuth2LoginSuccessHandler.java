package com.guider.securityconfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

        // Create User entity
        User user = new User();
        user.setUsername(email);
        user.setPassword("OAUTH_USER");
        user.setRole("ROLE_USER");
         
        userRepository.save(user);


        // Generate JWT Token
        String token = jwtUtil.generateToken(user);

        // Prepare JSON response
        Map<String, String> responseData = new HashMap<>();
        responseData.put("token", token);
        responseData.put("message", "Login successful!");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), responseData);
    }
}
