package com.guider.securityconfig;

import com.guider.services.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    @Qualifier("customUserDetailsService") // Specify the bean name
    private UserDetailsService userDetailsService;
    
    @Override
	protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain)
			throws jakarta.servlet.ServletException, IOException {
    	System.out.println("doing filter");
        String requestURI = request.getRequestURI();

        // Skip JWT processing for OAuth2 and public endpoints
        if ( requestURI.equals("/auth/customlogin") || requestURI.equals("/register/users" ) || requestURI.equals("/auth/oauthLogin") ) {
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("43");

        // Extract the Authorization header
        final String authorizationHeader = request.getHeader("Authorization");
        
        

        String email = null;
        String jwt = null;

        // Check if the header is present and starts with "Bearer "
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extract the token
            System.out.println("jwt"+jwt);
            try {
                // Validate the token and extract the username
                Claims claims = jwtUtil.validateToken(jwt);
                email = claims.get("email", String.class);
                System.out.println(email);
            } catch (Exception e) {
                // Log the error or handle it as needed
                System.err.println("JWT validation failed: " + e.getMessage());
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return; // Stop further execution
            }
        }

        // If the token is valid and no authentication is set in the context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load the user details from the database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);

            // Create an authentication object
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            
            // Set additional details (e.g., IP address, session ID)
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

	
		
	

	
	
}
