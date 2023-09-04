package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.Admin;
import com.project.springboothotelproject.enitites.Gender;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.repository.AdminRepository;
import com.project.springboothotelproject.repository.GuestRepository;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserDetailsService.class})
@ExtendWith(SpringExtension.class)
class CustomUserDetailsServiceTest {
    @MockBean
    private AdminRepository adminRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private GuestRepository guestRepository;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        when(adminRepository.findByAdminEmail((String) any()))
                .thenReturn(Optional.of(new Admin(1L, "ROLE_ADMIN", "jane.doe@example.org", "Jane123")));
        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("Jane123", actualLoadUserByUsernameResult.getPassword());
        verify(adminRepository).findByAdminEmail((String) any());
    }


    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(adminRepository.findByAdminEmail((String) any())).thenReturn(Optional.empty());
        new UsernameNotFoundException("Msg");
        new UsernameNotFoundException("Msg");
        when(guestRepository.findByEmail((String) any())).thenReturn(Optional.of(new Guest(1L, "Jane", "Doe", Gender.MALE,
                1, new Address("42 Main St", "Oxford", "MD", "21654"), "ROLE_GUEST", "jane.doe@example.org", "Jane123")));
        UserDetails actualLoadUserByUsernameResult = customUserDetailsService.loadUserByUsername("janedoe");
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("Jane123", actualLoadUserByUsernameResult.getPassword());
        verify(adminRepository).findByAdminEmail((String) any());
        verify(guestRepository).findByEmail((String) any());
    }

    @Test
    void testLoadUserByUsername7() throws UsernameNotFoundException {
        when(adminRepository.findByAdminEmail((String) any())).thenReturn(Optional.empty());
        new UsernameNotFoundException("Msg");
        new UsernameNotFoundException("Msg");
        when(guestRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        new UsernameNotFoundException("Msg");
        new UsernameNotFoundException("Msg");
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailsService.loadUserByUsername("janedoe"));
        verify(adminRepository).findByAdminEmail((String) any());
        verify(guestRepository).findByEmail((String) any());
    }
}

