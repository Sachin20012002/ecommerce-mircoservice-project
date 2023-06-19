package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.enums.PaymentType;
import com.sachin.cartmicroservice.model.Payment;

public interface PaymentService {

    Payment createPayment(PaymentType paymentType);
}
