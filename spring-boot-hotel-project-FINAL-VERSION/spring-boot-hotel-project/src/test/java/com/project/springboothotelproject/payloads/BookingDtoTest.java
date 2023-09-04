package com.project.springboothotelproject.payloads;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BookingDtoTest {

    @Test
    void testCanEqual() {
        assertFalse((new BookingDto()).canEqual("Other"));
    }

    @Test
    void testCanEqual2() {
        BookingDto bookingDto = new BookingDto();
        assertTrue(bookingDto.canEqual(new BookingDto()));
    }

    @Test
    void testConstructor() {
        BookingDto actualBookingDto = new BookingDto();
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualBookingDto.setCheckInDate(ofEpochDayResult);
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualBookingDto.setCheckOutDate(ofEpochDayResult1);
        actualBookingDto.setGuestId(1L);
        actualBookingDto.setHotelId(1L);
        actualBookingDto.setRoomId(1L);
        String actualToStringResult = actualBookingDto.toString();
        assertSame(ofEpochDayResult, actualBookingDto.getCheckInDate());
        assertSame(ofEpochDayResult1, actualBookingDto.getCheckOutDate());
        assertEquals(1L, actualBookingDto.getGuestId().longValue());
        assertEquals(1L, actualBookingDto.getHotelId().longValue());
        assertEquals(1L, actualBookingDto.getRoomId().longValue());
        assertEquals("BookingDto(hotelId=1, roomId=1, guestId=1, checkInDate=1970-01-02, checkOutDate=1970-01-02)",
                actualToStringResult);
    }


    @Test
    void testConstructor2() {
        BookingDto actualBookingDto = new BookingDto(1L, 1L, 1L, LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L));
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualBookingDto.setCheckInDate(ofEpochDayResult);
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualBookingDto.setCheckOutDate(ofEpochDayResult1);
        actualBookingDto.setGuestId(1L);
        actualBookingDto.setHotelId(1L);
        actualBookingDto.setRoomId(1L);
        String actualToStringResult = actualBookingDto.toString();
        assertSame(ofEpochDayResult, actualBookingDto.getCheckInDate());
        assertSame(ofEpochDayResult1, actualBookingDto.getCheckOutDate());
        assertEquals(1L, actualBookingDto.getGuestId().longValue());
        assertEquals(1L, actualBookingDto.getHotelId().longValue());
        assertEquals(1L, actualBookingDto.getRoomId().longValue());
        assertEquals("BookingDto(hotelId=1, roomId=1, guestId=1, checkInDate=1970-01-02, checkOutDate=1970-01-02)",
                actualToStringResult);
    }

    @Test
    void testEquals() {
        BookingDto bookingDto = new BookingDto();
        assertEquals(bookingDto, bookingDto);
        int expectedHashCodeResult = bookingDto.hashCode();
        assertEquals(expectedHashCodeResult, bookingDto.hashCode());
    }

    @Test
    void testEquals2() {
        BookingDto bookingDto = new BookingDto();
        BookingDto bookingDto1 = new BookingDto();
        assertEquals(bookingDto, bookingDto1);
        int expectedHashCodeResult = bookingDto.hashCode();
        assertEquals(expectedHashCodeResult, bookingDto1.hashCode());
    }

    @Test
    void testEquals3() {
        BookingDto bookingDto = new BookingDto(1L, 1L, 1L, LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L));
        assertNotEquals(bookingDto, new BookingDto());
    }


    @Test
    void testEquals4() {
        BookingDto bookingDto = new BookingDto();
        assertNotEquals(bookingDto, new BookingDto(1L, 1L, 1L, LocalDate.ofEpochDay(1L), LocalDate.ofEpochDay(1L)));
    }

    @Test
    void testEquals5() {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setRoomId(1L);
        assertNotEquals(bookingDto, new BookingDto());
    }
}

