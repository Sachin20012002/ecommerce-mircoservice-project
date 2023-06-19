package com.sachin.ordermicroservice.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String customerName;
    private String email;
    private String phoneNumber;
    private Double amount;
}
