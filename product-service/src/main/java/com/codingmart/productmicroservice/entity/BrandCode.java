package com.codingmart.productmicroservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BrandCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_generator")
    @SequenceGenerator(name = "code_generator",allocationSize = 1,initialValue = 100)
    Long id;
}
