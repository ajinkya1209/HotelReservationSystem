package com.project.springboothotelproject.service;

import java.util.List;

import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.payloads.RoomDto;
public interface RoomService {
    String addRoom(RoomDto roomDto);
    List<Room> getAllRooms();
    String deleteRooms(Long roomId);
	List<Room> getAllRoomsByHotelId(Long hotelId);
    List<Room> getAllEmptyRooms(Long hotelId);
    String deleteAllRooms(Hotel hotel);

}
