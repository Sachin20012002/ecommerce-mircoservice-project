package com.sachin.filterservice.dto;

import com.sachin.filterservice.model.ProductES;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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


    public ProductDTO(ProductES productES){
        this.id=productES.getId();
        this.name=productES.getName();
        this.price=productES.getPrice();
        this.maximumRetailPrice=productES.getMaximumRetailPrice();
        this.finalDiscountedPrice=productES.getFinalDiscountedPrice();
        this.quantity=productES.getQuantity();
        this.color=productES.getColor();
        this.code=productES.getCode();
        this.brandName=productES.getBrandName();
        this.description=productES.getDescription();
        this.active=productES.getActive();
        this.childCategoryId=productES.getChildCategoryId();
        this.supplierId=productES.getSupplierId();
        this.typeName=productES.getTypeName();
        this.sizeNames=productES.getSizeNames();
        this.imageUrls=productES.getImageUrls();
        this.productDetails=productES.getProductDetails()
                .stream()
                .map(ProductDetailDTO::new).collect(Collectors.toList());
    }
}
