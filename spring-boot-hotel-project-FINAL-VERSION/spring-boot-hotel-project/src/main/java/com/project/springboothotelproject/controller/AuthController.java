package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.models.AuthenticateRequest;
import com.project.springboothotelproject.models.AuthenticateResponse;
import com.project.springboothotelproject.service.AuthenticateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class AuthController {
	@Autowired
	private AuthenticateService authenticateService;

	// Endpoint for authenticating user login
	@PostMapping("/login")
	public ResponseEntity<AuthenticateResponse> authenticate(@Valid @RequestBody AuthenticateRequest request)
	{
		return ResponseEntity.ok(authenticateService.authenticate(request));
	}
}
