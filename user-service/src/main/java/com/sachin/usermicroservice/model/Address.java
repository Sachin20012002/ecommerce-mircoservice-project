package com.sachin.usermicroservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Address {

    @Id
    private String id;
    private String houseNo;
    private String streetName;
    private String area;
    private String city;
    private String state;
    private String country;

}
