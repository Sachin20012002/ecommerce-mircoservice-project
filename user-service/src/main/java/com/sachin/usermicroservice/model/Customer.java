package com.sachin.usermicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class Customer {
    @Id
    private String id;
    @DBRef
    private List<Address> shippingAddresses;
    private String cartId;
    private String defaultShippingAddressId;
    @DBRef
    private User userDetails;
}
