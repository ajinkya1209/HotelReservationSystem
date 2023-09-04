package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.payloads.AdminDto;
import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class AdminController {
    @Autowired
    private AdminService adminService;

    // Endpoint for adding an admin
    @PostMapping("/add-admin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody AdminDto adminDto)
    {
        String message=adminService.addAdmin(adminDto);
        return  new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving a list of all admins
    @GetMapping("/get-admin")
    public ResponseEntity<List<AdminDto>> viewAdmins()
    {
        List<AdminDto> list=adminService.getAllAdmin();
        return  new ResponseEntity<List<AdminDto>>(list,HttpStatus.OK);
    }
}
