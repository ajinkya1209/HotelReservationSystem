package com.project.springboothotelproject.enitites;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class BookingTest {

    @Test
    void testConstructor() {
        Booking actualBooking = new Booking();
        actualBooking.setBookingAmount(1);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualBooking.setCheckInDate(ofEpochDayResult);
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualBooking.setCheckOutDate(ofEpochDayResult1);
        Guest guest = new Guest();
        actualBooking.setGuest(guest);
        Hotel hotel = new Hotel();
        actualBooking.setHotel(hotel);
        Room room = new Room();
        actualBooking.setRoom(room);
        actualBooking.toString();
        assertEquals(1, actualBooking.getBookingAmount().intValue());
        assertSame(ofEpochDayResult, actualBooking.getCheckInDate());
        assertSame(ofEpochDayResult1, actualBooking.getCheckOutDate());
        assertSame(guest, actualBooking.getGuest());
        assertSame(hotel, actualBooking.getHotel());
        assertSame(room, actualBooking.getRoom());
    }


    @Test
    void testConstructor2() {
        LocalDate checkInDate = LocalDate.ofEpochDay(1L);
        LocalDate checkOutDate = LocalDate.ofEpochDay(1L);
        Guest guest = new Guest();
        Room room = new Room();
        Booking actualBooking = new Booking(checkInDate, checkOutDate, 1, guest, room, new Hotel());
        actualBooking.setBookingAmount(1);
        LocalDate ofEpochDayResult = LocalDate.ofEpochDay(1L);
        actualBooking.setCheckInDate(ofEpochDayResult);
        LocalDate ofEpochDayResult1 = LocalDate.ofEpochDay(1L);
        actualBooking.setCheckOutDate(ofEpochDayResult1);
        Guest guest1 = new Guest();
        actualBooking.setGuest(guest1);
        Hotel hotel = new Hotel();
        actualBooking.setHotel(hotel);
        Room room1 = new Room();
        actualBooking.setRoom(room1);
        actualBooking.toString();
        assertEquals(1, actualBooking.getBookingAmount().intValue());
        assertSame(ofEpochDayResult, actualBooking.getCheckInDate());
        assertSame(ofEpochDayResult1, actualBooking.getCheckOutDate());
        assertSame(guest1, actualBooking.getGuest());
        assertSame(hotel, actualBooking.getHotel());
        assertSame(room1, actualBooking.getRoom());
    }
}

