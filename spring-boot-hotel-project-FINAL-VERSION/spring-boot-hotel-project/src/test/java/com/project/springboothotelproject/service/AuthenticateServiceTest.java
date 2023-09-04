package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.models.AuthenticateRequest;
import com.project.springboothotelproject.models.AuthenticateResponse;
import com.project.springboothotelproject.repository.AdminRepository;
import com.project.springboothotelproject.repository.GuestRepository;
import com.project.springboothotelproject.security.JwtService;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticateService.class})
@ExtendWith(SpringExtension.class)
class AuthenticateServiceTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticateService authenticateService;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private JwtService jwtService;

    @Test
    void testAuthenticate() throws AuthenticationException {
        when(customUserDetailsService.loadUserByUsername((String) any())).thenReturn(new Admin());
        when(jwtService.extractAuthority((UserDetails) any())).thenReturn("JaneDoe");
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticateResponse actualAuthenticateResult = authenticateService
                .authenticate(new AuthenticateRequest("janedoe", "Jane123"));
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertNull(actualAuthenticateResult.getUserId());
        verify(customUserDetailsService).loadUserByUsername((String) any());
        verify(jwtService).extractAuthority((UserDetails) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }

    @Test
    void testAuthenticate2() throws AuthenticationException {
        when(customUserDetailsService.loadUserByUsername((String) any())).thenReturn(new Admin());
        when(jwtService.extractAuthority((UserDetails) any())).thenReturn("ROLE_GUEST");
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(guestRepository.findByEmail((String) any())).thenReturn(Optional.of(new Guest()));
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticateResponse actualAuthenticateResult = authenticateService
                .authenticate(new AuthenticateRequest("jackdoe", "Jack123"));
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertNull(actualAuthenticateResult.getUserId());
        verify(customUserDetailsService).loadUserByUsername((String) any());
        verify(jwtService).extractAuthority((UserDetails) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(guestRepository).findByEmail((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }


    @Test
    void testAuthenticate3() throws AuthenticationException {
        when(customUserDetailsService.loadUserByUsername((String) any())).thenReturn(new Admin());
        when(jwtService.extractAuthority((UserDetails) any())).thenReturn("ROLE_ADMIN");
        when(jwtService.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(adminRepository.findByAdminEmail((String) any())).thenReturn(Optional.of(new Admin()));
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticateResponse actualAuthenticateResult = authenticateService
                .authenticate(new AuthenticateRequest("janedoe", "Jane123"));
        assertEquals("ABC123", actualAuthenticateResult.getToken());
        assertNull(actualAuthenticateResult.getUserId());
        verify(customUserDetailsService).loadUserByUsername((String) any());
        verify(jwtService).extractAuthority((UserDetails) any());
        verify(jwtService).generateToken((UserDetails) any());
        verify(adminRepository).findByAdminEmail((String) any());
        verify(authenticationManager).authenticate((Authentication) any());
    }
}

