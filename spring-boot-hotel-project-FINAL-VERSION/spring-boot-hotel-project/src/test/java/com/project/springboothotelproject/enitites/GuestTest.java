package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class GuestTest {

    @Test
    void testConstructor() {
        Guest actualGuest = new Guest();
        assertNull(actualGuest.getAuthorities());
        assertNull(actualGuest.getUsername());
        assertTrue(actualGuest.isAccountNonExpired());
        assertTrue(actualGuest.isAccountNonLocked());
        assertTrue(actualGuest.isCredentialsNonExpired());
        assertTrue(actualGuest.isEnabled());
    }
}

