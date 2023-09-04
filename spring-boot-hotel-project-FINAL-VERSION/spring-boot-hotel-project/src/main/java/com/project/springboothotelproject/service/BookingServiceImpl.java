package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.*;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.BookingDto;
import com.project.springboothotelproject.repository.*;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookRepo;
    @Autowired
    private GuestRepository guestRepo;
    @Autowired
    private ModelMapper maper;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PaymentRepository paymentRepository;

    //Adding a new booking to the database
    @Override
    public String addNewBooking(BookingDto bookingDto) {

        // Fetch hotel, room, and guest based on provided IDs
        Hotel hotel= hotelRepository.findById(bookingDto.getHotelId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Hotel Id"));
        Room room= roomRepository.findById(bookingDto.getRoomId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Room Id"));
        Guest guest=guestRepo.findById(bookingDto.getGuestId()).orElseThrow(()-> new ResourceNotFoundException("Wrong Customer Name.. Register first!!"));
        Payment payment=paymentRepository.findByRoomId(room.getId());

        // Check if payment is cleared and room is empty before booking
        if(payment.getPaymentStatus().equals("CLEARED") && (room.getRoomStatus().equals("AVAILABLE"))) {
            // Check if booking date is before check out date
            if(bookingDto.getCheckInDate().isBefore(bookingDto.getCheckOutDate())){
                // Create and save the booking
                Booking booking = maper.map(bookingDto, Booking.class);
                booking.setBookingAmount(payment.getPaymentAmount());
                booking.setGuest(guest);
                booking.setRoom(room);
                booking.setHotel(hotel);
                room.setRoomStatus("RESERVED");
                roomRepository.save(room);
                bookRepo.save(booking);

                return ("Booking with Id " + booking.getId() + " added successfully");
            }
            else return ("Check In Date must be before Check out Date");
        }
        else return ("Room Already Booked");
    }

    // Retrieves a list of all bookings
    @Override
    public List<Booking> getAllBookings() {
        return bookRepo.findAll();
    }

    //Retrieves a list of bookings of particular guest
    @Override
    public List<Booking> getBookingByGuestId(Long guestId) {
        return bookRepo.findAllByGuestGuestId(guestId).orElseThrow(()-> new ResourceNotFoundException("Invalid Guest"));
    }

    //Delete booking of particular room of particular guest
    @Override
    public String deleteBooking(Long guestId,Long roomId) {
        String msg="Cancelled Successfully";
        Room room=roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFoundException("Invalid Room Id"));
        List<Booking> bookings=getBookingByGuestId(guestId);
        Booking booking=bookRepo.findByRoomId(room.getId());
        Payment payment=paymentRepository.findByRoomId(roomId);

        // Set room status to empty, delete payment, and delete booking
        room.setRoomStatus("AVAILABLE");
        roomRepository.save(room);
        paymentRepository.delete(payment);
        bookRepo.delete(booking);

        return ("Booking associated with Room ID "+room.getId()+" "+msg);
    }
}
