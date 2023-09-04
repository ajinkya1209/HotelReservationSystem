package com.project.springboothotelproject.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

//This filter is responsible for processing and validating JWT tokens sent in
// the Authorization header of incoming HTTP requests
@Component
@RequiredArgsConstructor  //It will generate a constructor for final field which is automatically injected with the required dependencies.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal
            (@NonNull HttpServletRequest request,
             @NonNull HttpServletResponse response,
             @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        // Check if the request contains an Authorization header with a Bearer token
            final String authHeader=request.getHeader("Authorization");
            final String jwtToken;
            final String userEmail;
            final Long userId;

        // If the header is missing or doesn't start with "Bearer ", continue with the filter chain
            if(authHeader==null || !authHeader.startsWith("Bearer "))
            {
                filterChain.doFilter(request,response);
                return;
            }

        // Extract the JWT token from the header
            jwtToken=authHeader.substring(7);
            userEmail=jwtService.extractUserName(jwtToken);

        // Check if the user's email is valid and not already authenticated
            if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                UserDetails userDetails=this.userDetailsService.loadUserByUsername(userEmail);

                // Validate the JWT token against the user details
                if(jwtService.isTokenValid(jwtToken,userDetails)) {
                    // Create an authentication token and set it in the security context
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        // Continue with the filter chain
            filterChain.doFilter(request,response);
    }
}
