package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.payloads.GuestDto;
import com.project.springboothotelproject.payloads.UpdateGuestDto;
import com.project.springboothotelproject.service.GuestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/guest")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class GuestController {
    @Autowired
    private GuestService guestService;

    // Endpoint for adding a new guest
    @PostMapping("/create-user")
    public ResponseEntity<?> addNewGuest(@Valid @RequestBody GuestDto guestDto)
    {
        String message=guestService.addGuest(guestDto);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving a list of all guests
    @GetMapping("/get-user")
    public  ResponseEntity<List<GuestDto>> viewGuests()
    {
        List<GuestDto> lists=guestService.getAllGuests();
        return new ResponseEntity<List<GuestDto>>(lists,HttpStatus.OK);
    }

    // Endpoint for updating a guest's information
    @PutMapping("/update-user/{guestId}")
    public ResponseEntity<?> updateGuest(@PathVariable Long guestId, @RequestBody UpdateGuestDto guestDto) {
        String message=guestService.updateGuest(guestId,guestDto);
        return new ResponseEntity<>(new ApiResponse(message),HttpStatus.OK);
    }
}
