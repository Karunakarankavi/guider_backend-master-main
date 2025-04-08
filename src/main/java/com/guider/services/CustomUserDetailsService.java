package com.guider.services;

import com.guider.entity.User;
import com.guider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("******************** Loading user: " + username);

        // Try to find user by email or phone number
        User user = userRepository.findByUsernameOrEmailOrPhoneNumber(username, "" , username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email or phone number: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), // Or use user.getUsername() if that's the preferred unique identifier
                user.getPassword(),
                Collections.emptyList() // You can populate authorities here if needed
        );
    }
}
