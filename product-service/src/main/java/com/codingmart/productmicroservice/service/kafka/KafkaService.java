package com.codingmart.productmicroservice.service.kafka;

import com.codingmart.productmicroservice.dto.ProductDTO;
import com.codingmart.productmicroservice.dto.ProductDetailDTO;
import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.entity.Size;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaService {
    private final KafkaTemplate<String,String> kafkaTemplate;

    @SneakyThrows
    public void addProductEvent(Product product) {
        ProductDTO productDTO=ProductDTO.builder().id(product.getId())
                .name(product.getName())
                .brandName(product.getBrand().getName())
                .productDetails(product.getProductDetails()
                        .stream()
                        .map(productDetail -> ProductDetailDTO.builder()
                                .id(productDetail.getId())
                                .name(productDetail.getName())
                                .value(productDetail.getValue())
                                .build()).collect(Collectors.toList()))
                .childCategoryId(product.getChildCategoryId())
                .color(product.getColor())
                .code(product.getCode())
                .description(product.getDescription())
                .finalDiscountedPrice(product.getFinalDiscountedPrice())
                .imageUrls(product.getImages().stream().map(Image::getUrl).collect(Collectors.toList()))
                .price(product.getPrice())
                .maximumRetailPrice(product.getMaximumRetailPrice())
                .quantity(product.getQuantity())
                .sizeNames(product.getAvailableSizes().stream().map(Size::getName).collect(Collectors.toList()))
                .supplierId(product.getSupplierId())
                .active(product.getActive())
                .typeName(product.getType().getName())
                .build();
        System.out.println(productDTO);
        ObjectMapper objectMapper=new ObjectMapper();
        String message=objectMapper.writeValueAsString(productDTO);
        log.info(String.format("sending kafka message to POST topic %s",message));
        kafkaTemplate.send("POST",message);
    }
}
