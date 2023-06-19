package com.sachin.cartmicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    private String id;
    @DBRef
    private List<CartItem> cartItems;
    private String customerId;
    @DBRef
    private CheckOut checkOut;
}
