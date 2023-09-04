package com.project.springboothotelproject.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.project.springboothotelproject.enitites.Address;
import com.project.springboothotelproject.enitites.HotelType;
import org.junit.jupiter.api.Test;

class HotelDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new HotelDto()).canEqual("Other"));
    }


    @Test
    void testCanEqual2() {
        HotelDto hotelDto = new HotelDto();
        assertTrue(hotelDto.canEqual(new HotelDto()));
    }


    @Test
    void testConstructor() {
        HotelDto actualHotelDto = new HotelDto();
        actualHotelDto.setAboutHotel("About Hotel");
        Address address = new Address("42 Main St", "Oxford", "MD", "21654");

        actualHotelDto.setAddress(address);
        actualHotelDto.setContactNo("Contact No");
        actualHotelDto.setHotelAmenities("Hotel Amenities");
        actualHotelDto.setHotelId(1L);
        actualHotelDto.setHotelName("Hotel Name");
        actualHotelDto.setHotelType(HotelType.TWO_STAR);
        actualHotelDto.setRoomCapacity(1);
        String actualToStringResult = actualHotelDto.toString();
        assertEquals("About Hotel", actualHotelDto.getAboutHotel());
        assertSame(address, actualHotelDto.getAddress());
        assertEquals("Contact No", actualHotelDto.getContactNo());
        assertEquals("Hotel Amenities", actualHotelDto.getHotelAmenities());
        assertEquals(1L, actualHotelDto.getHotelId().longValue());
        assertEquals("Hotel Name", actualHotelDto.getHotelName());
        assertEquals(HotelType.TWO_STAR, actualHotelDto.getHotelType());
        assertEquals(1, actualHotelDto.getRoomCapacity().intValue());
        assertEquals("HotelDto(hotelId=1, hotelName=Hotel Name, aboutHotel=About Hotel, address=Address(streetAddress=42"
                + " Main St, city=Oxford, state=MD, zipCode=21654), contactNo=Contact No, hotelType=TWO_STAR, hotelAmenities"
                + "=Hotel Amenities, roomCapacity=1)", actualToStringResult);
    }


    @Test
    void testConstructor2() {
        Address address = new Address("42 Main St", "Oxford", "MD", "21654");

        HotelDto actualHotelDto = new HotelDto(1L, "Hotel Name", "About Hotel", address, "Contact No", HotelType.TWO_STAR,
                "Hotel Amenities", 1);
        actualHotelDto.setAboutHotel("About Hotel");
        Address address1 = new Address("42 Main St", "Oxford", "MD", "21654");

        actualHotelDto.setAddress(address1);
        actualHotelDto.setContactNo("Contact No");
        actualHotelDto.setHotelAmenities("Hotel Amenities");
        actualHotelDto.setHotelId(1L);
        actualHotelDto.setHotelName("Hotel Name");
        actualHotelDto.setHotelType(HotelType.TWO_STAR);
        actualHotelDto.setRoomCapacity(1);
        String actualToStringResult = actualHotelDto.toString();
        assertEquals("About Hotel", actualHotelDto.getAboutHotel());
        Address address2 = actualHotelDto.getAddress();
        assertSame(address1, address2);
        assertEquals(address, address2);
        assertEquals("Contact No", actualHotelDto.getContactNo());
        assertEquals("Hotel Amenities", actualHotelDto.getHotelAmenities());
        assertEquals(1L, actualHotelDto.getHotelId().longValue());
        assertEquals("Hotel Name", actualHotelDto.getHotelName());
        assertEquals(HotelType.TWO_STAR, actualHotelDto.getHotelType());
        assertEquals(1, actualHotelDto.getRoomCapacity().intValue());
        assertEquals("HotelDto(hotelId=1, hotelName=Hotel Name, aboutHotel=About Hotel, address=Address(streetAddress=42"
                + " Main St, city=Oxford, state=MD, zipCode=21654), contactNo=Contact No, hotelType=TWO_STAR, hotelAmenities"
                + "=Hotel Amenities, roomCapacity=1)", actualToStringResult);
    }


    @Test
    void testEquals() {
        assertNotEquals(new HotelDto(), null);
        assertNotEquals(new HotelDto(), "Different type to HotelDto");
    }


    @Test
    void testEquals2() {
        HotelDto hotelDto = new HotelDto();
        assertEquals(hotelDto, hotelDto);
        int expectedHashCodeResult = hotelDto.hashCode();
        assertEquals(expectedHashCodeResult, hotelDto.hashCode());
    }


    @Test
    void testEquals3() {
        HotelDto hotelDto = new HotelDto();
        HotelDto hotelDto1 = new HotelDto();
        assertEquals(hotelDto, hotelDto1);
        int expectedHashCodeResult = hotelDto.hashCode();
        assertEquals(expectedHashCodeResult, hotelDto1.hashCode());
    }


    @Test
    void testEquals4() {
        HotelDto hotelDto = new HotelDto(1L, "Hotel Name", "About Hotel",
                new Address("42 Main St", "Oxford", "MD", "216547"), "Contact No", HotelType.TWO_STAR, "Hotel Amenities", 1);
        assertNotEquals(hotelDto, new HotelDto());
    }


    @Test
    void testEquals5() {
        HotelDto hotelDto = new HotelDto();
        assertNotEquals(hotelDto, new HotelDto(1L, "Hotel Name", "About Hotel",
                new Address("42 Main St", "Oxford", "MD", "216547"), "Contact No", HotelType.TWO_STAR, "Hotel Amenities", 1));
    }


    @Test
    void testEquals6() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelName("Hotel Name");
        assertNotEquals(hotelDto, new HotelDto());
    }



    @Test
    void testEquals8() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setAddress(new Address("42 Main St", "Oxford", "MD", "21654"));
        assertNotEquals(hotelDto, new HotelDto());
    }

    @Test
    void testEquals9() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setContactNo("Contact No");
        assertNotEquals(hotelDto, new HotelDto());
    }

    @Test
    void testEquals10() {
        HotelDto hotelDto = new HotelDto();
        hotelDto.setHotelAmenities("Hotel Amenities");
        assertNotEquals(hotelDto, new HotelDto());
    }

    @Test
    void testEquals11() {
        HotelDto hotelDto = new HotelDto(1L, "Hotel Name", "About Hotel", mock(Address.class), "Contact No",
                HotelType.TWO_STAR, "Hotel Amenities", 1);
        assertNotEquals(hotelDto, new HotelDto());
    }


    @Test
    void testEquals12() {
        HotelDto hotelDto = new HotelDto();

        HotelDto hotelDto1 = new HotelDto();
        hotelDto1.setHotelName("Hotel Name");
        assertNotEquals(hotelDto, hotelDto1);
    }

    @Test
    void testEquals13() {
        HotelDto hotelDto = new HotelDto();

        HotelDto hotelDto1 = new HotelDto();
        hotelDto1.setAboutHotel("About Hotel");
        assertNotEquals(hotelDto, hotelDto1);
    }



    @Test
    void testEquals14() {
        HotelDto hotelDto = new HotelDto();

        HotelDto hotelDto1 = new HotelDto();
        hotelDto1.setHotelType(HotelType.TWO_STAR);
        assertNotEquals(hotelDto, hotelDto1);
    }


    @Test
    void testEquals15() {
        HotelDto hotelDto = new HotelDto();

        HotelDto hotelDto1 = new HotelDto();
        hotelDto1.setRoomCapacity(100);
        assertNotEquals(hotelDto, hotelDto1);
    }
}

