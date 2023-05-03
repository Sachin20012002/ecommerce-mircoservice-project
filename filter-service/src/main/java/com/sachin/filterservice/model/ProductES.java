package com.sachin.filterservice.model;


import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Document(indexName = "product")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductES {

    private Long id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Double)
    private Double maximumRetailPrice;

    @Field(type = FieldType.Double)
    private Double finalDiscountedPrice;

    @Field(type = FieldType.Long)
    private Long quantity;

    @Field(type = FieldType.Keyword)
    private String code;

    @Field(type = FieldType.Keyword)
    private String color;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Long)
    private Long childCategoryId;

    @Field(type = FieldType.Long)
    private Long supplierId;

    @Field(type = FieldType.Keyword)
    private String brandName;

    @Field(type = FieldType.Keyword)
    private String typeName;

    @Field(type = FieldType.Boolean)
    private Boolean active;


    @Field(type = FieldType.Keyword)
    private List<String> sizeNames;

    @Field(type = FieldType.Keyword)
    private List<String> imageUrls;

    @Field(type = FieldType.Nested, includeInParent = true)
    private List<ProductDetailES> productDetails;

}