package com.codingmart.productmicroservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Data
public class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Double maximumRetailPrice;
    private Double finalDiscountedPrice;
    private Long quantity;
    private String code;
    private String color;
    private String description;
    private Long childCategoryId;
    private Long supplierId;
    private String brandName;
    private String typeName;
    private Boolean active;
    private List<String> sizeNames;
    private List<String> imageUrls;
    private List<ProductDetailDTO> productDetails;
}
