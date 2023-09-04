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

import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.enitites.Hotel;
import com.project.springboothotelproject.enitites.Payment;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.enitites.RoomType;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.PaymentDto;
import com.project.springboothotelproject.repository.BookingRepository;
import com.project.springboothotelproject.repository.GuestRepository;
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

@ContextConfiguration(classes = {PaymentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PaymentServiceImplTest {
    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private GuestRepository guestRepository;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    @MockBean
    private RoomRepository roomRepository;

    @Test
    void testAddPayment() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(roomRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("Invalid Room Id"));
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.addPayment(new PaymentDto()));
        verify(guestRepository).findById((Long) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testAddPayment2() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.empty());
        when(roomRepository.findById((Long) any())).thenReturn(Optional.of(new Room()));
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.addPayment(new PaymentDto()));
        verify(guestRepository).findById((Long) any());
    }

    @Test
    void testAddPayment3() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        Room room = mock(Room.class);
        when(room.getRoomStatus()).thenReturn("Room Status");
        Optional<Room> ofResult = Optional.of(room);
        when(roomRepository.findById((Long) any())).thenReturn(ofResult);
        assertEquals("Room is already Booked", paymentServiceImpl.addPayment(new PaymentDto()));
        verify(guestRepository).findById((Long) any());
        verify(roomRepository).findById((Long) any());
        verify(room).getRoomStatus();
    }


    @Test
    void testAddPayment4() {
        when(guestRepository.findById((Long) any())).thenReturn(Optional.of(new Guest()));
        when(roomRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("Msg");
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.addPayment(new PaymentDto()));
        verify(guestRepository).findById((Long) any());
        verify(roomRepository).findById((Long) any());
    }

    @Test
    void testGetAllPayments() {
        ArrayList<Payment> paymentList = new ArrayList<>();
        when(paymentRepository.findAll()).thenReturn(paymentList);
        List<Payment> actualAllPayments = paymentServiceImpl.getAllPayments();
        assertSame(paymentList, actualAllPayments);
        assertTrue(actualAllPayments.isEmpty());
        verify(paymentRepository).findAll();
    }

    @Test
    void testGetAllPayments2() {
        when(paymentRepository.findAll()).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.getAllPayments());
        verify(paymentRepository).findAll();
    }

    @Test
    void testDeletePayment() {
        doNothing().when(paymentRepository).delete((Payment) any());
        when(paymentRepository.findById((Long) any())).thenReturn(Optional.of(new Payment()));
        assertEquals("Payment having id 1 deleted successfully", paymentServiceImpl.deletePayment(1L));
        verify(paymentRepository).findById((Long) any());
        verify(paymentRepository).delete((Payment) any());
    }

    @Test
    void testDeletePayment2() {
        doThrow(new ResourceNotFoundException("Msg")).when(paymentRepository).delete((Payment) any());
        when(paymentRepository.findById((Long) any())).thenReturn(Optional.of(new Payment()));
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.deletePayment(1L));
        verify(paymentRepository).findById((Long) any());
        verify(paymentRepository).delete((Payment) any());
    }

    @Test
    void testDeletePayment3() {
        doNothing().when(paymentRepository).delete((Payment) any());
        when(paymentRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> paymentServiceImpl.deletePayment(1L));
        verify(paymentRepository).findById((Long) any());
    }
}

