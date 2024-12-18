package com.example.payment.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    public String processPayment(double amount) {
        boolean paymentSuccess = new Random().nextBoolean();
        return paymentSuccess ? "Payment Successful" : "Payment Failed";
    }
}
