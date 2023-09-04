package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.models.AuthenticateRequest;
import com.project.springboothotelproject.models.AuthenticateResponse;
import com.project.springboothotelproject.repository.AdminRepository;
import com.project.springboothotelproject.repository.GuestRepository;
import com.project.springboothotelproject.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    //Responsible for loading user details from the database based on the provided username.
    private final CustomUserDetailsService customUserDetailsService;

    //Used to authenticate the user's credentials (username and password).
    private final AuthenticationManager authenticationManager;

    //Used to generate and validate JWT tokens.
    private final JwtService jwtService;

    //Used to access guest-related data from the database.
    private final GuestRepository guestRepository;

    //Used to access admin-related data from the database.
    private final AdminRepository adminRepository;

    // Authenticate a user based on provided credentials
    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getUserName(),
                        authenticateRequest.getPassword()
                )
        );
        Long userId = null;

        // Load user details for generating JWT token
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticateRequest.getUserName());

        // Generate JWT token
        String jwtToken = jwtService.generateToken(userDetails);

        // Extract the user's authority (role)
        String authority = jwtService.extractAuthority(userDetails);

        // If the user has "ROLE_GUEST" authority, get the user's ID
        if (authority.equals("ROLE_GUEST")) {
            Guest guest = guestRepository.findByEmail(authenticateRequest.getUserName()).orElseThrow();
            userId = guest.getGuestId();
        }

        // If the user has "ROLE_GUEST" authority, get the user's ID
        else if(authority.equals("ROLE_ADMIN")) {
            Admin admin=adminRepository.findByAdminEmail(authenticateRequest.getUserName()).orElseThrow();
            userId=admin.getAdminId();
        }

        // Build and return the AuthenticateResponse
        return AuthenticateResponse.builder()
                .token(jwtToken)
                .userId(userId)
                .build();
    }
}
