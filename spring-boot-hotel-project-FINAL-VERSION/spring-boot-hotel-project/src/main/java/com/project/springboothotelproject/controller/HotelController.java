package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.payloads.HotelDto;
import com.project.springboothotelproject.service.HotelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/hotel")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class HotelController {
    @Autowired
    private HotelService hotelService;

    // Endpoint for adding a new hotel
    @PostMapping("/add-hotel")
    public ResponseEntity<?> addNewHotel(@Valid @RequestBody HotelDto hotelDto) {

        String message = hotelService.addHotels(hotelDto);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving a list of all hotels
    @GetMapping("/get-hotel")
    public ResponseEntity<List<HotelDto>> viewRooms() {

        List<HotelDto> list = hotelService.getHotels();
        return new ResponseEntity<List<HotelDto>>(list, HttpStatus.OK);
    }

    // Endpoint for deleting a hotel by hotel ID
    @DeleteMapping("/delete-hotel/{hotelId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long hotelId) {

        String message = hotelService.deleteHotels(hotelId);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.OK);
    }

    // Endpoint for updating various fields of a hotel
    @PutMapping("/update-hotel/{hotelId}")
    public ResponseEntity<?> editHotelFields(@PathVariable Long hotelId,@Valid @RequestBody HotelDto hotelDto) {

        String message = hotelService.editAllFields(hotelId, hotelDto);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.OK);
    }

    // Endpoint for getting the list of hotel cities
    @GetMapping("/get-cities")
    public ResponseEntity<List<String>> getHotelCities()
    {
        List<String> cityList=hotelService.getAllCities();
        return new ResponseEntity<>(cityList,HttpStatus.OK);
    }
}
