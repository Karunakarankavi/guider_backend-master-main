package com.guider.controller;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google")

public class OAuth2Controller {

	@GetMapping("/login-success")
	public String loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User, Principal principal) {
	    if (oauth2User == null && principal == null) {
	        System.out.println("User is not authenticated. Redirecting to login page.");
	        return "redirect:/login"; // Redirect to login page if user is not authenticated
	    }

	    if (oauth2User != null) {
	        String email = oauth2User.getAttribute("email");
	        String name = oauth2User.getAttribute("name");
	        System.out.println("OAuth2User: Email = " + email + ", Name = " + name);
	        return "Login successful! Welcome, " + name + " (" + email + ")";
	    } else if (principal != null) {
	        System.out.println("Principal: " + principal.getName());
	        return "Login successful! Welcome, " + principal.getName();
	    } else {
	        System.out.println("Unexpected state. Redirecting to login page.");
	        return "redirect:/login";
	    }
	}
    
    @GetMapping("/login")
    public String loginSuccessx(@AuthenticationPrincipal OAuth2User oauth2User) {
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        System.out.println(email);
        System.out.println(oauth2User);
        return "Login successful! Welcome, " + name + " (" + email + ")";
    }

    @GetMapping("/login-failure")
    public String loginFailure() {
    	System.out.println("login failed");
        return "Login failed!";
    }
}