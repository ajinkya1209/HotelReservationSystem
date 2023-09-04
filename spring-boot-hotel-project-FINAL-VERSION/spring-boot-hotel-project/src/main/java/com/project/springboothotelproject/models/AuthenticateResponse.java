package com.project.springboothotelproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateResponse {

    // Field for storing the authentication token
    private String token;

    // Field for storing the user ID
    private Long userId;
}
