package com.sachin.filterservice.model;

import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "product_detail")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailES {

    private Long id;
    private String name;
    private String value;

}
