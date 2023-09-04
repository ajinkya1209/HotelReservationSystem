package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class HotelTest {

    @Test
    void testConstructor() {
        Hotel actualHotel = new Hotel();
        actualHotel.setAboutHotel("About Hotel");
        Address address = new Address("42 Main St", "Oxford", "MD", "21654");

        actualHotel.setAddress(address);
        actualHotel.setContactNo("Contact No");
        actualHotel.setHotelAmenities("Hotel Amenities");
        actualHotel.setHotelId(1L);
        actualHotel.setHotelName("Hotel Name");
        actualHotel.setHotelType(HotelType.TWO_STAR);
        actualHotel.setRoomCapacity(1);
        assertEquals("About Hotel", actualHotel.getAboutHotel());
        assertSame(address, actualHotel.getAddress());
        assertEquals("Contact No", actualHotel.getContactNo());
        assertEquals("Hotel Amenities", actualHotel.getHotelAmenities());
        assertEquals(1L, actualHotel.getHotelId().longValue());
        assertEquals("Hotel Name", actualHotel.getHotelName());
        assertEquals(HotelType.TWO_STAR, actualHotel.getHotelType());
        assertEquals(1, actualHotel.getRoomCapacity().intValue());
    }


    @Test
    void testConstructor2() {
        Address address = new Address("42 Main St", "Oxford", "MD", "21654");

        Hotel actualHotel = new Hotel(1L, "Hotel Name", "About Hotel", address, "Contact No", HotelType.TWO_STAR,
                "Hotel Amenities", 1);
        actualHotel.setAboutHotel("About Hotel");
        Address address1 = new Address("42 Main St", "Oxford", "MD", "21654");

        actualHotel.setAddress(address1);
        actualHotel.setContactNo("Contact No");
        actualHotel.setHotelAmenities("Hotel Amenities");
        actualHotel.setHotelId(1L);
        actualHotel.setHotelName("Hotel Name");
        actualHotel.setHotelType(HotelType.TWO_STAR);
        actualHotel.setRoomCapacity(1);
        assertEquals("About Hotel", actualHotel.getAboutHotel());
        Address address2 = actualHotel.getAddress();
        assertSame(address1, address2);
        assertEquals(address, address2);
        assertEquals("Contact No", actualHotel.getContactNo());
        assertEquals("Hotel Amenities", actualHotel.getHotelAmenities());
        assertEquals(1L, actualHotel.getHotelId().longValue());
        assertEquals("Hotel Name", actualHotel.getHotelName());
        assertEquals(HotelType.TWO_STAR, actualHotel.getHotelType());
        assertEquals(1, actualHotel.getRoomCapacity().intValue());
    }
}

