package com.project.springboothotelproject.exceptionhandling;

import com.project.springboothotelproject.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

// Global exception handler for REST controllers
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Exception handler for ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException exc)
    {
        String msg=exc.getMessage();
        return new ResponseEntity<ApiResponse>((new ApiResponse(msg)), HttpStatus.NOT_FOUND);
    }

    // Exception handler for MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgsNotValidExceptionHandler(MethodArgumentNotValidException exc)
    {
        Map<String,String> resp=new HashMap<String, String>();

        // Iterate through validation errors and populate the response map
        exc.getBindingResult().getAllErrors().forEach((error)-> {
            String fieldName=((FieldError)error).getField();
            String message=error.getDefaultMessage();
            resp.put(fieldName, message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }

    // Exception handler for other runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> runtimeExceptionHandler(RuntimeException exc)
    {
        String msg=exc.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(msg),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
