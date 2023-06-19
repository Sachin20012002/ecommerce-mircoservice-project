package com.sachin.cartmicroservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCartMessage {
    private String customerId;
    private String cartId;
}
