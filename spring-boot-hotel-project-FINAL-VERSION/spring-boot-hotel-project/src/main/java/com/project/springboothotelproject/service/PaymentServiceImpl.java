package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Guest;
import com.project.springboothotelproject.enitites.Payment;
import com.project.springboothotelproject.enitites.Room;
import com.project.springboothotelproject.exceptionhandling.ResourceNotFoundException;
import com.project.springboothotelproject.payloads.PaymentDto;
import com.project.springboothotelproject.repository.BookingRepository;
import com.project.springboothotelproject.repository.GuestRepository;
import com.project.springboothotelproject.repository.PaymentRepository;
import com.project.springboothotelproject.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ModelMapper maper;
    @Autowired
    private GuestRepository guestRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    // Add a new payment
    @Override
    public String addPayment(PaymentDto paymentDto)
    {
        Guest guest=guestRepository.findById(paymentDto.getGuestId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Guest Id"));
        Room room=roomRepository.findById(paymentDto.getRoomId()).orElseThrow(()-> new ResourceNotFoundException("Invalid Room Id"));

        // Check if the room is available
        if(room.getRoomStatus().equals("EMPTY")) {
            // Check if payment amount matches room price
            if (paymentDto.getPaymentAmount().equals(room.getRoomPrice())) {
                if(paymentRepository.findByRoomId(room.getId())==null) {
                    // Create and save the payment
                    Payment payment = maper.map(paymentDto, Payment.class);
                    payment.setPaymentStatus("CLEARED");
                    payment.setGuest(guest);
                    payment.setRoom(room);
                    paymentRepository.save(payment);
                } else return ("Payment already in the processing!! Couldn't process this payment.. Please book for another room");
                return ("Payment added successfully");
            } else return ("Amount not matched");
        }
        else return ("Room is already Booked");
    }

    // Retrieving details of all payments
    @Override
    public List<Payment> getAllPayments()
    {
        return paymentRepository.findAll();
    }

    // Delete a payment by payment Id
    @Override
    public String deletePayment(Long paymentId) {
        Payment payment=paymentRepository.findById(paymentId).orElseThrow(()-> new ResourceNotFoundException("Invalid payment id"));
        paymentRepository.delete(payment);
        return ("Payment having id "+paymentId+" deleted successfully");
    }
}
