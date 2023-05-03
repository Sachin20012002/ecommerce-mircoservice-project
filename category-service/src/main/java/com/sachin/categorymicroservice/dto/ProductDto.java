package com.sachin.categorymicroservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private String imageUrl;
}
