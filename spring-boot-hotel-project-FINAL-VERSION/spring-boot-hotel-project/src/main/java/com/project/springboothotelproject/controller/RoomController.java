package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.payloads.RoomDto;
import com.project.springboothotelproject.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/room")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class RoomController {
    @Autowired
    private RoomService roomService;

    // Endpoint for adding a new room in a hotel
    @PostMapping("/add-room")
    public ResponseEntity<?> addNewRoom(@Valid @RequestBody RoomDto roomDto)
    {
        String message=roomService.addRoom(roomDto);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving details of all rooms
    @GetMapping("/get-room")
    public ResponseEntity<List<Room>> viewRooms()
    {
        List<Room> list=roomService.getAllRooms();
        return new ResponseEntity<List<Room>>(list,HttpStatus.OK);
    }


    // Endpoint for retrieving details of rooms by hotel ID
    @GetMapping("/get-room/{hotelId}")
    public ResponseEntity<List<Room>> viewRoomsByHotelName(@PathVariable Long hotelId)
    {
        List<Room> roomsList=roomService.getAllRoomsByHotelId(hotelId);
        return new ResponseEntity<List<Room>>(roomsList,HttpStatus.OK);
    }

    // Endpoint for deleting a room by room ID
    @DeleteMapping("/delete-room/{roomId}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long roomId)
    {
        String message=roomService.deleteRooms(roomId);
        return new ResponseEntity<>(new ApiResponse(message),HttpStatus.OK);
    }

    // Endpoint for retrieving details of available (empty) rooms by hotel ID
    @GetMapping("/get-empty-rooms/{hotelId}")
    public ResponseEntity<List<Room>> viewAvailableRooms(@PathVariable Long hotelId) {
        List<Room> roomList=roomService.getAllEmptyRooms(hotelId);
        return new ResponseEntity<>(roomList,HttpStatus.OK);
    }
}
