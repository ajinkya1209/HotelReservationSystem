package com.project.springboothotelproject.service;

import com.project.springboothotelproject.enitites.Payment;
import com.project.springboothotelproject.payloads.PaymentDto;
import java.util.List;
public interface PaymentService {
    String addPayment(PaymentDto paymentDto);
    List<Payment> getAllPayments();
    String deletePayment(Long paymentId);
}
