package com.project.springboothotelproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.enitites.RoomType;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.RoomDto;
import com.project.springboothotelproject.repository.HotelRepository;
import com.project.springboothotelproject.repository.RoomRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ModelMapper maper;
    @Autowired
    private HotelRepository hotelRepository;

    // Add a new room
    @Override
    public String addRoom(RoomDto roomDto) {

       Hotel hotel= hotelRepository.findById(roomDto.getHotelId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Hotel Id"));
       Long currentCapacity=roomRepository.findSumofRoomsByHotelId(roomDto.getHotelId());

        // Check if the hotel's room capacity is not exceeded
          if(hotel.getRoomCapacity()>currentCapacity) {
              //Create and save room
           Room room = maper.map(roomDto, Room.class);
           room.setRoomPrice(roomDto.getRoomType().getPrice());
           System.out.println("This is the room price : "+room.getRoomPrice());
           room.setRoomStatus("AVAILABLE");
           room.setHotel(hotel);
           roomRepository.save(room);
           return ("Room having room id " + room.getId() + " added successfully");
       }
       return ("Room can't be added as Hotel Room Accommodations are full");
    }

    // Retrieving details of all rooms
    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    //Delete room as per room Id
    @Override
    public String deleteRooms(Long roomId)
    {
        String msg="Deleted Successfully";
        Room room=roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Invalid Room Id!!"));
        roomRepository.delete(room);
        return ("Room having id "+room.getId()+" "+msg);
    }

    //Getting the list of Rooms as per hotel id
	@Override
	public List<Room> getAllRoomsByHotelId(Long hotelId) {
        return roomRepository.findAllByHotelHotelId(hotelId).orElseThrow(()-> new ResourceNotFoundException("Invalid Hotel Id!!"));
	}

    //Getting the list of available rooms as per hotel id
    @Override
    public List<Room> getAllEmptyRooms(Long hotelId) {
       List<Room> rooms= getAllRoomsByHotelId(hotelId);
       List<Room> emptyRooms=new ArrayList<>();
       for(Room r:rooms) {
           if(r.getRoomStatus().equals("EMPTY"))
               emptyRooms.add(r);
       }
       return emptyRooms;
    }

    //Delete all rooms - used when Admin delete the hotels
    @Override
    public String deleteAllRooms(Hotel hotel) {
        String msg="Deleted Successfully";
        List<Room> rooms=roomRepository.findAllByHotelHotelId(hotel.getHotelId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Id!!"));
        roomRepository.deleteAll(rooms);
        return ("Rooms for hotel "+hotel.getHotelName()+" "+msg);
    }
}