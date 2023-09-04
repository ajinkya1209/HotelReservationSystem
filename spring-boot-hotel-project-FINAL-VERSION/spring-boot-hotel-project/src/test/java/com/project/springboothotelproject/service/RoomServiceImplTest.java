package com.project.springboothotelproject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.enitites.RoomType;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.RoomDto;
import com.project.springboothotelproject.repository.HotelRepository;
import com.project.springboothotelproject.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoomServiceImpl.class})
@ExtendWith(SpringExtension.class)
class RoomServiceImplTest {
    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomServiceImpl roomServiceImpl;

    @Test
    void testAddRoom() {
        Hotel hotel = new Hotel();
        hotel.setRoomCapacity(1);
        Optional<Hotel> ofResult = Optional.of(hotel);
        when(hotelRepository.findById((Long) any())).thenReturn(ofResult);
        when(roomRepository.findSumofRoomsByHotelId((Long) any())).thenReturn(1L);
        assertEquals("Room can't be added as Hotel Room Accommodations are full", roomServiceImpl.addRoom(new RoomDto()));
        verify(hotelRepository).findById((Long) any());
        verify(roomRepository).findSumofRoomsByHotelId((Long) any());
    }

    @Test
    void testAddRoom2() {
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Msg");
        when(roomRepository.findSumofRoomsByHotelId((Long) any())).thenReturn(1L);
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.addRoom(new RoomDto()));
        verify(hotelRepository).findById((Long) any());
    }


    @Test
    void testGetAllRooms() {
        ArrayList<Room> roomList = new ArrayList<>();
        when(roomRepository.findAll()).thenReturn(roomList);
        List<Room> actualAllRooms = roomServiceImpl.getAllRooms();
        assertSame(roomList, actualAllRooms);
        assertTrue(actualAllRooms.isEmpty());
        verify(roomRepository).findAll();
    }

    @Test
    void testGetAllRooms2() {
        when(roomRepository.findAll()).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.getAllRooms());
        verify(roomRepository).findAll();
    }

    @Test
    void testDeleteRooms() {
        doNothing().when(roomRepository).delete((Room) any());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertEquals("Room having id null Deleted Successfully", roomServiceImpl.deleteRooms(1L));
        verify(roomRepository).findById((Long) any());
        verify(roomRepository).delete((Room) any());
    }

    @Test
    void testDeleteRooms2() {
        doThrow(new ResourceNotFoundException("Deleted Successfully")).when(roomRepository).delete((Room) any());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.deleteRooms(1L));
        verify(roomRepository).findById((Long) any());
        verify(roomRepository).delete((Room) any());
    }


    @Test
    void testDeleteRooms3() {
        doNothing().when(roomRepository).delete((Room) any());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Msg");
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.deleteRooms(1L));
        verify(roomRepository).findById((Long) any());
    }


    @Test
    void testGetAllRoomsByHotelId() {
        ArrayList<Room> roomList = new ArrayList<>();
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.of(roomList));
        List<Room> actualAllRoomsByHotelId = roomServiceImpl.getAllRoomsByHotelId(1L);
        assertSame(roomList, actualAllRoomsByHotelId);
        assertTrue(actualAllRoomsByHotelId.isEmpty());
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }


    @Test
    void testGetAllRoomsByHotelId2() {
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.getAllRoomsByHotelId(1L));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }


    @Test
    void testGetAllRoomsByHotelId3() {
        when(roomRepository.findAllByHotelHotelId((Long) any()))
                .thenThrow(new ResourceNotFoundException("Invalid Hotel Id!!"));
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.getAllRoomsByHotelId(1L));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }


    @Test
    void testGetAllEmptyRooms() {
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertTrue(roomServiceImpl.getAllEmptyRooms(1L).isEmpty());
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }


    @Test
    void testGetAllEmptyRooms2() {
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.getAllEmptyRooms(1L));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }

    @Test
    void testGetAllEmptyRooms3() {
        when(roomRepository.findAllByHotelHotelId((Long) any()))
                .thenThrow(new ResourceNotFoundException("Invalid Hotel Id!!"));
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.getAllEmptyRooms(1L));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }

    @Test
    void testGetAllEmptyRooms4() {
        ArrayList<Room> roomList = new ArrayList<>();
        roomList.add(new Room(RoomType.DELUXE, "EMPTY", 1, "EMPTY", "EMPTY", new Hotel()));
        Optional<List<Room>> ofResult = Optional.of(roomList);
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(ofResult);
        assertEquals(1, roomServiceImpl.getAllEmptyRooms(1L).size());
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }


    @Test
    void testDeleteAllRooms() {
        doNothing().when(roomRepository).deleteAll((Iterable<Room>) any());
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertEquals("Rooms for hotel null Deleted Successfully", roomServiceImpl.deleteAllRooms(new Hotel()));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
        verify(roomRepository).deleteAll((Iterable<Room>) any());
    }

    @Test
    void testDeleteAllRooms2() {
        doThrow(new ResourceNotFoundException("Deleted Successfully")).when(roomRepository)
                .deleteAll((Iterable<Room>) any());
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.deleteAllRooms(new Hotel()));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
        verify(roomRepository).deleteAll((Iterable<Room>) any());
    }


    @Test
    void testDeleteAllRooms3() {
        doNothing().when(roomRepository).deleteAll((Iterable<Room>) any());
        when(roomRepository.findAllByHotelHotelId((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> roomServiceImpl.deleteAllRooms(new Hotel()));
        verify(roomRepository).findAllByHotelHotelId((Long) any());
    }

}

