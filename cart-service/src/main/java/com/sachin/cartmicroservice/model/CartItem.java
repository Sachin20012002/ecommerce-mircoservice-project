package com.sachin.cartmicroservice.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItem {

    @Id
    private String id;
    private Long productId;
    private Long quantity;
    private String variation;
    private Long supplierId;
}
