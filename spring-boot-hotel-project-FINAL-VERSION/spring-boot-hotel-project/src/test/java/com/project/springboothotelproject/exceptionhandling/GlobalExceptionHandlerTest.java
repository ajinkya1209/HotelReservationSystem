package com.project.springboothotelproject.exceptionhandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.springboothotelproject.payloads.ApiResponse;

import java.lang.reflect.Executable;
import java.util.Map;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

    @Test
    void testResourceNotFoundExceptionHandler() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<ApiResponse> actualResourceNotFoundExceptionHandlerResult = globalExceptionHandler
                .resourceNotFoundExceptionHandler(new ResourceNotFoundException("Msg"));
        assertTrue(actualResourceNotFoundExceptionHandlerResult.hasBody());
        assertTrue(actualResourceNotFoundExceptionHandlerResult.getHeaders().isEmpty());
        assertEquals(404, actualResourceNotFoundExceptionHandlerResult.getStatusCodeValue());
        assertEquals("Msg", actualResourceNotFoundExceptionHandlerResult.getBody().getMesg());
    }


    @Test
    void testMethodArgsNotValidExceptionHandler() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<Map<String, String>> actualMethodArgsNotValidExceptionHandlerResult = globalExceptionHandler
                .methodArgsNotValidExceptionHandler(
                        new MethodArgumentNotValidException((Executable) null, new BindException("Target", "Object Name")));
        assertTrue(actualMethodArgsNotValidExceptionHandlerResult.hasBody());
        assertEquals(400, actualMethodArgsNotValidExceptionHandlerResult.getStatusCodeValue());
        assertTrue(actualMethodArgsNotValidExceptionHandlerResult.getHeaders().isEmpty());
    }


    @Test
    void testRuntimeExceptionHandler() {
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        ResponseEntity<ApiResponse> actualRuntimeExceptionHandlerResult = globalExceptionHandler
                .runtimeExceptionHandler(new RuntimeException());
        assertTrue(actualRuntimeExceptionHandlerResult.hasBody());
        assertTrue(actualRuntimeExceptionHandlerResult.getHeaders().isEmpty());
        assertEquals(500, actualRuntimeExceptionHandlerResult.getStatusCodeValue());
        assertNull(actualRuntimeExceptionHandlerResult.getBody().getMesg());
    }
}

