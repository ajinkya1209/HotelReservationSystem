package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AdminTest {

    @Test
    void testConstructor() {
        Admin actualAdmin = new Admin();
        assertNull(actualAdmin.getAuthorities());
        assertNull(actualAdmin.getPassword());
        assertNull(actualAdmin.getUsername());
        assertTrue(actualAdmin.isAccountNonExpired());
        assertTrue(actualAdmin.isAccountNonLocked());
        assertTrue(actualAdmin.isCredentialsNonExpired());
        assertTrue(actualAdmin.isEnabled());
    }
}

