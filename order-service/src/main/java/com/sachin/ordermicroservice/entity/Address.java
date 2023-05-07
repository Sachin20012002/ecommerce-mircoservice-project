package com.sachin.ordermicroservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String houseNo;
    private String streetName;
    private String area;
    private String city;
    private String state;
    private String country;

}
