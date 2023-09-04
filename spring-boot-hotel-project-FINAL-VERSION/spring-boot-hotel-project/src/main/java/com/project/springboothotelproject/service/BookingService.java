package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Booking;
import com.project.springboothotelproject.payloads.BookingDto;
import java.util.List;
public interface BookingService {
    String addNewBooking(BookingDto bookingDto);
    List<Booking> getAllBookings();
    List<Booking> getBookingByGuestId(Long guestId);
    String deleteBooking(Long guestId,Long roomId);
}
