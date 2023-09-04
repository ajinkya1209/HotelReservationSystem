package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.enitites.Booking;
import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.payloads.BookingDto;
import com.project.springboothotelproject.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/booking")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class BookingController {
    @Autowired
    BookingService bookingService;

    // Endpoint for adding a new booking
    @PostMapping("/add-booking")
    public ResponseEntity<?> addBooking(@Valid @RequestBody BookingDto bookingDto)
    {
        // Call the booking service to add a new booking
        String message=bookingService.addNewBooking(bookingDto);

        // Return a response with a message and HTTP status code 201 (Created)
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving a list of all bookings
    @GetMapping("/get-booking")
    public ResponseEntity<List<Booking>> viewBookingDetails()
    {
        List<Booking> list=bookingService.getAllBookings();
        return new ResponseEntity<List<Booking>>(list,HttpStatus.OK);
    }

    // Endpoint for retrieving booking details by guest ID
    @GetMapping("/get-booking/{guestId}")
    public ResponseEntity<List<Booking>> viewBookingDetailsByGuest(@PathVariable Long guestId)
    {
        List<Booking> bookingList=bookingService.getBookingByGuestId(guestId);
        return new ResponseEntity<>(bookingList,HttpStatus.OK);
    }

    // Endpoint for canceling a booking by guest ID and room ID
    @DeleteMapping("/delete-booking/guest/{guestId}/room/{roomId}")
    public ResponseEntity<?> cancelBooking(@PathVariable Long guestId,@PathVariable Long roomId) {

        // Call the booking service to cancel a booking
        String message=bookingService.deleteBooking(guestId,roomId);

        // Return a response with a message and HTTP status code 200 (OK)
        return new ResponseEntity<>(new ApiResponse(message),HttpStatus.OK);
    }
}
