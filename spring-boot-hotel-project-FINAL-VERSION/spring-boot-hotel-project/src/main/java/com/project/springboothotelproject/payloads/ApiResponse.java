package com.project.springboothotelproject.payloads;

import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@ToString
public class ApiResponse {

    // Field for storing the timestamp when the response is created
    private LocalDateTime timeStamp;

    // Field for storing the message in the response
    private String mesg;

    // Constructor to create an ApiResponse with a message
    public ApiResponse(String mesg)
    {
        // Initialize the message and timestamp
        this.mesg=mesg;
        this.timeStamp=LocalDateTime.now();
    }
}
