package com.codingmart.productmicroservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String value;
}
