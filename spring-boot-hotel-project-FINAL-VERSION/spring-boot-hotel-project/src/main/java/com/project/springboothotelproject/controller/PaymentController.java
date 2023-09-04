package com.project.springboothotelproject.controller;

import com.project.springboothotelproject.enitites.Payment;
import com.project.springboothotelproject.payloads.ApiResponse;
import com.project.springboothotelproject.payloads.PaymentDto;
import com.project.springboothotelproject.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/payment")
@CrossOrigin("http://localhost:3000/") // Allow cross-origin requests from specified URL
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    // Endpoint for adding a new payment
    @PostMapping("/add-payment")
    public ResponseEntity<?> addNewPayment(@Valid @RequestBody PaymentDto paymentDto)
    {
        String message=paymentService.addPayment(paymentDto);
        return new ResponseEntity<>(new ApiResponse(message), HttpStatus.CREATED);
    }

    // Endpoint for retrieving a list of all payments
    @GetMapping("/get-payment")
    public ResponseEntity<List<Payment>> viewAllPayments()
    {
        List<Payment> list=paymentService.getAllPayments();
        return  new ResponseEntity<List<Payment>>(list,HttpStatus.OK);
    }
}
