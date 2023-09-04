package com.project.springboothotelproject.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.project.springboothotelproject.enitites.RoomType;
import org.junit.jupiter.api.Test;

class RoomDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new RoomDto()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        RoomDto roomDto = new RoomDto();
        assertTrue(roomDto.canEqual(new RoomDto()));
    }

    @Test
    void testConstructor() {
        RoomDto actualRoomDto = new RoomDto();
        actualRoomDto.setAboutRoom("About Room");
        actualRoomDto.setHotelId(1L);
        actualRoomDto.setRoomFacilities("Room Facilities");
        actualRoomDto.setRoomType(RoomType.DELUXE);
        String actualToStringResult = actualRoomDto.toString();
        assertEquals("About Room", actualRoomDto.getAboutRoom());
        assertEquals(1L, actualRoomDto.getHotelId().longValue());
        assertEquals("Room Facilities", actualRoomDto.getRoomFacilities());
        assertEquals(RoomType.DELUXE, actualRoomDto.getRoomType());
        assertEquals("RoomDto(roomType=DELUXE, aboutRoom=About Room, roomFacilities=Room Facilities, hotelId=1)",
                actualToStringResult);
    }


    @Test
    void testConstructor2() {
        RoomDto actualRoomDto = new RoomDto(RoomType.DELUXE, "About Room", "Room Facilities", 1L);
        actualRoomDto.setAboutRoom("About Room");
        actualRoomDto.setHotelId(1L);
        actualRoomDto.setRoomFacilities("Room Facilities");
        actualRoomDto.setRoomType(RoomType.DELUXE);
        String actualToStringResult = actualRoomDto.toString();
        assertEquals("About Room", actualRoomDto.getAboutRoom());
        assertEquals(1L, actualRoomDto.getHotelId().longValue());
        assertEquals("Room Facilities", actualRoomDto.getRoomFacilities());
        assertEquals(RoomType.DELUXE, actualRoomDto.getRoomType());
        assertEquals("RoomDto(roomType=DELUXE, aboutRoom=About Room, roomFacilities=Room Facilities, hotelId=1)",
                actualToStringResult);
    }
}

