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

import com.project.springboothotelproject.enitites.Booking;
import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.Payment;
import com.project.springboothotelproject.enitites.PaymentMode;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.BookingDto;
import com.project.springboothotelproject.repository.BookingRepository;
import com.project.springboothotelproject.repository.GuestRepository;
import com.project.springboothotelproject.repository.HotelRepository;
import com.project.springboothotelproject.repository.PaymentRepository;
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

@ContextConfiguration(classes = {BookingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookingServiceImplTest {
    @MockBean
    private BookingRepository bookingRepository;

    @Autowired
    private BookingServiceImpl bookingServiceImpl;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PaymentRepository paymentRepository;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    void testAddNewBooking() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.of(new Hotel()));
        when(paymentRepository.findByRoomId((Long) any())).thenThrow(new ResourceNotFoundException("Msg"));
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.addNewBooking(new BookingDto()));
        verify(guestRepository).findById((Long) any());
        verify(hotelRepository).findById((Long) any());
        verify(paymentRepository).findByRoomId((Long) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testAddNewBooking2() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.of(new Hotel()));
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.addNewBooking(new BookingDto()));
        verify(guestRepository).findById((Long) any());
        verify(hotelRepository).findById((Long) any());
        verify(roomRepository).findById((Long) any());
    }


    @Test
    void testAddNewBooking3() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.addNewBooking(new BookingDto()));
        verify(hotelRepository).findById((Long) any());
    }



    @Test
    void testAddNewBooking4() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.of(new Hotel()));
        Payment payment = mock(Payment.class);
        when(payment.getPaymentStatus()).thenReturn("Payment Status");
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(payment);
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertEquals("Room Already Booked", bookingServiceImpl.addNewBooking(new BookingDto()));
        verify(guestRepository).findById((Long) any());
        verify(hotelRepository).findById((Long) any());
        verify(paymentRepository).findByRoomId((Long) any());
        verify(payment).getPaymentStatus();
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testAddNewBooking5() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(hotelRepository.findById((Long) any())).thenReturn(Optional.of(new Hotel()));
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(mock(Payment.class));
        when(roomRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Msg");
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.addNewBooking(new BookingDto()));
        verify(hotelRepository).findById((Long) any());
        verify(roomRepository).findById((Long) any());
    }


    @Test
    void testGetAllBookings() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findAll()).thenReturn(bookingList);
        List<Booking> actualAllBookings = bookingServiceImpl.getAllBookings();
        assertSame(bookingList, actualAllBookings);
        assertTrue(actualAllBookings.isEmpty());
        verify(bookingRepository).findAll();
    }

    @Test
    void testGetBookingByGuestId() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.of(bookingList));
        List<Booking> actualBookingByGuestId = bookingServiceImpl.getBookingByGuestId(1L);
        assertSame(bookingList, actualBookingByGuestId);
        assertTrue(actualBookingByGuestId.isEmpty());
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
    }


    @Test
    void testGetBookingByGuestId2() {
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.getBookingByGuestId(1L));
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
    }

    @Test
    void testGetBookingByGuestId3() {
        when(bookingRepository.findAllByGuestGuestId((Long) any()))
                .thenThrow(new ResourceNotFoundException("Invalid Guest"));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.getBookingByGuestId(1L));
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
    }

    @Test
    void testDeleteBooking() {
        when(bookingRepository.findByRoomId((Long) any())).thenReturn(new Booking());
        doNothing().when(bookingRepository).delete((Booking) any());
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        doNothing().when(paymentRepository).delete((Payment) any());
        when(roomRepository.save((Room) any())).thenReturn(new Room());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertEquals("Booking associated with Room ID null Cancelled Successfully",
                bookingServiceImpl.deleteBooking(1L, 1L));
        verify(bookingRepository).findByRoomId((Long) any());
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
        verify(bookingRepository).delete((Booking) any());
        verify(paymentRepository).findByRoomId((Long) any());
        verify(paymentRepository).delete((Payment) any());
        verify(roomRepository).save((Room) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testDeleteBooking2() {
        when(bookingRepository.findByRoomId((Long) any())).thenReturn(new Booking());
        doNothing().when(bookingRepository).delete((Booking) any());
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        doNothing().when(paymentRepository).delete((Payment) any());
        when(roomRepository.save((Room) any())).thenThrow(new ResourceNotFoundException("Cancelled Successfully"));
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.deleteBooking(1L, 1L));
        verify(bookingRepository).findByRoomId((Long) any());
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
        verify(paymentRepository).findByRoomId((Long) any());
        verify(roomRepository).save((Room) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testDeleteBooking3() {
        when(bookingRepository.findByRoomId((Long) any())).thenReturn(new Booking());
        doNothing().when(bookingRepository).delete((Booking) any());
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.empty());
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        doNothing().when(paymentRepository).delete((Payment) any());
        when(roomRepository.save((Room) any())).thenReturn(new Room());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.deleteBooking(1L, 1L));
        verify(bookingRepository).findAllByGuestGuestId((Long) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testDeleteBooking5() {
        when(bookingRepository.findByRoomId((Long) any())).thenReturn(new Booking());
        doNothing().when(bookingRepository).delete((Booking) any());
        when(bookingRepository.findAllByGuestGuestId((Long) any())).thenReturn(Optional.of(new ArrayList<>()));
        when(paymentRepository.findByRoomId((Long) any())).thenReturn(new Payment());
        doNothing().when(paymentRepository).delete((Payment) any());
        when(roomRepository.save((Room) any())).thenReturn(new Room());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Msg");
        new ResourceNotFoundException("Msg");
        assertThrows(ResourceNotFoundException.class, () -> bookingServiceImpl.deleteBooking(1L, 1L));
        verify(roomRepository).findById((Long) any());
    }
}

