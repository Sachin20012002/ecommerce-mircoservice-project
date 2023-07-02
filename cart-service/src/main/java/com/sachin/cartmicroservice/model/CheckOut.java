package com.sachin.cartmicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckOut {
    @Id
    private String id;
    private String shippingAddressId;
    @DBRef
    private Payment payment;

}
