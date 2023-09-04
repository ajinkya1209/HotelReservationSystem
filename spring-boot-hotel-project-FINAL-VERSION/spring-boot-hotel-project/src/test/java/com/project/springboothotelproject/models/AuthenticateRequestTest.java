package com.project.springboothotelproject.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AuthenticateRequestTest {

    @Test
    void testConstructor() {
        AuthenticateRequest actualAuthenticateRequest = new AuthenticateRequest();
        actualAuthenticateRequest.setPassword("Jane123");
        actualAuthenticateRequest.setUserName("janedoe");
        String actualToStringResult = actualAuthenticateRequest.toString();
        assertEquals("Jane123", actualAuthenticateRequest.getPassword());
        assertEquals("janedoe", actualAuthenticateRequest.getUserName());
        assertEquals("AuthenticateRequest(userName=janedoe, password=Jane123)", actualToStringResult);
    }



    @Test
    void testEquals() {
        assertNotEquals(new AuthenticateRequest("janedoe", "Jane123"), null);
        assertNotEquals(new AuthenticateRequest("janedoe", "Jane123"), "Different type to AuthenticateRequest");
    }

}

