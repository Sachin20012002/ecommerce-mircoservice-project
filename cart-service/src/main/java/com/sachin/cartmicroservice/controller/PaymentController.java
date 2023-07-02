package com.sachin.cartmicroservice.controller;

import com.sachin.cartmicroservice.enums.PaymentType;
import com.sachin.cartmicroservice.model.Payment;
import com.sachin.cartmicroservice.response.GenericResponse;
import com.sachin.cartmicroservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;


}
