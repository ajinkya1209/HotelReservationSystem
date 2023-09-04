package com.project.springboothotelproject.exceptionhandling;

// Custom exception class for resource not found scenarios
public class ResourceNotFoundException extends RuntimeException{

    // Constructor to create the exception with a custom error message
    public ResourceNotFoundException(String msg)
    {
        super(msg);
    }

}
