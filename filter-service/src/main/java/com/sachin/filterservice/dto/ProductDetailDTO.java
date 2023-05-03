package com.sachin.filterservice.dto;

import com.sachin.filterservice.model.ProductDetailES;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    private Long id;
    private String name;
    private String value;

    public ProductDetailDTO(ProductDetailES productDetailES){
        this.id=productDetailES.getId();
        this.name=productDetailES.getName();
        this.value=productDetailES.getValue();
    }
}
