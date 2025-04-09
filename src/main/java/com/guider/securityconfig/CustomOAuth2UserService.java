package com.guider.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.guider.repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	System.out.println("loading users");
        OAuth2User user = super.loadUser(userRequest);
        
        // Get role from additional parameters
        Object role = userRequest.getAdditionalParameters().get("role");
        System.out.println("loadrole"+role);
        if (role == null) {
            role = "user"; // default role
        }
        
        // Save user with role
//        String email = user.getAttribute("email");
//        User dbUser = userRepository.findByEmail(email)
//            .orElse(new User(email));
//            
//        dbUser.setRole("ROLE_" + role.toUpperCase());
//        userRepository.save(dbUser);
        
        return user;
    }
}
