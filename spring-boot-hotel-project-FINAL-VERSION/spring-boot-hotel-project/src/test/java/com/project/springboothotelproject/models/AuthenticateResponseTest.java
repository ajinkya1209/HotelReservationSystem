package com.project.springboothotelproject.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AuthenticateResponseTest {

    @Test
    void testCanEqual() {
        assertFalse((new AuthenticateResponse()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        AuthenticateResponse authenticateResponse = new AuthenticateResponse();
        assertTrue(authenticateResponse.canEqual(new AuthenticateResponse()));
    }

    @Test
    void testConstructor() {
        AuthenticateResponse actualAuthenticateResponse = new AuthenticateResponse();
        actualAuthenticateResponse.setToken("ABC123");
        actualAuthenticateResponse.setUserId(1L);
        String actualToStringResult = actualAuthenticateResponse.toString();
        assertEquals("ABC123", actualAuthenticateResponse.getToken());
        assertEquals(1L, actualAuthenticateResponse.getUserId().longValue());
        assertEquals("AuthenticateResponse(token=ABC123, userId=1)", actualToStringResult);
    }


    @Test
    void testConstructor2() {
        AuthenticateResponse actualAuthenticateResponse = new AuthenticateResponse("ABC123", 1L);
        actualAuthenticateResponse.setToken("ABC123");
        actualAuthenticateResponse.setUserId(1L);
        String actualToStringResult = actualAuthenticateResponse.toString();
        assertEquals("ABC123", actualAuthenticateResponse.getToken());
        assertEquals(1L, actualAuthenticateResponse.getUserId().longValue());
        assertEquals("AuthenticateResponse(token=ABC123, userId=1)", actualToStringResult);
    }


    @Test
    void testEquals() {
        assertNotEquals(new AuthenticateResponse(), null);
        assertNotEquals(new AuthenticateResponse(), "Different type to AuthenticateResponse");
    }


    @Test
    void testEquals2() {
        AuthenticateResponse authenticateResponse = new AuthenticateResponse("ABC123", 1L);
        assertNotEquals(authenticateResponse, new AuthenticateResponse());
    }

    @Test
    void testEquals3() {
        AuthenticateResponse authenticateResponse = new AuthenticateResponse();
        assertNotEquals(authenticateResponse, new AuthenticateResponse("ABC123", 1L));
    }
}

