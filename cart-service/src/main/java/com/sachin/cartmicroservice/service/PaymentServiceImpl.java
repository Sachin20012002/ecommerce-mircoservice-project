package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.enums.PaymentStatus;
import com.sachin.cartmicroservice.enums.PaymentType;
import com.sachin.cartmicroservice.model.Payment;
import com.sachin.cartmicroservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    @Override
    public Payment createPayment(PaymentType paymentType) {
        return Payment.builder()
                .paymentType(paymentType)
                .paymentStatus(PaymentStatus.INITIATED)
                .build();
    }
}
