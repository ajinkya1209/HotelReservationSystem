package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testConstructor() {
        Room actualRoom = new Room();
        actualRoom.setAboutRoom("About Room");
        Hotel hotel = new Hotel();
        actualRoom.setHotel(hotel);
        actualRoom.setRoomFacilities("Room Facilities");
        actualRoom.setRoomPrice(1);
        actualRoom.setRoomStatus("Room Status");
        actualRoom.setRoomType(RoomType.DELUXE);
        assertEquals("About Room", actualRoom.getAboutRoom());
        assertSame(hotel, actualRoom.getHotel());
        assertEquals("Room Facilities", actualRoom.getRoomFacilities());
        assertEquals(1, actualRoom.getRoomPrice().intValue());
        assertEquals("Room Status", actualRoom.getRoomStatus());
        assertEquals(RoomType.DELUXE, actualRoom.getRoomType());
    }


    @Test
    void testConstructor2() {
        Room actualRoom = new Room(RoomType.DELUXE, "About Room", 1, "Room Facilities", "Room Status", new Hotel());
        actualRoom.setAboutRoom("About Room");
        Hotel hotel = new Hotel();
        actualRoom.setHotel(hotel);
        actualRoom.setRoomFacilities("Room Facilities");
        actualRoom.setRoomPrice(1);
        actualRoom.setRoomStatus("Room Status");
        actualRoom.setRoomType(RoomType.DELUXE);
        assertEquals("About Room", actualRoom.getAboutRoom());
        assertSame(hotel, actualRoom.getHotel());
        assertEquals("Room Facilities", actualRoom.getRoomFacilities());
        assertEquals(1, actualRoom.getRoomPrice().intValue());
        assertEquals("Room Status", actualRoom.getRoomStatus());
        assertEquals(RoomType.DELUXE, actualRoom.getRoomType());
    }
}

