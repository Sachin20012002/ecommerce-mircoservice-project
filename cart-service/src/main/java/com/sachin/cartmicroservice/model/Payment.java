package com.sachin.cartmicroservice.model;

import com.sachin.cartmicroservice.enums.PaymentStatus;
import com.sachin.cartmicroservice.enums.PaymentType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;

    private String paymentId;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private LocalDateTime paymentDateTime;
    private PaymentType paymentType;

}


