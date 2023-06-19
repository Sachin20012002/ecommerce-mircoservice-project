package com.sachin.categorymicroservice.service;

import com.sachin.categorymicroservice.dto.ProductDto;
import com.sachin.ecommerce_microservice_project.product.ProductServiceGrpc;
import com.sachin.ecommerce_microservice_project.product.ProductServiceRequest;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrpcService {
    @GrpcClient("product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public List<ProductDto> getAllProductsByChildCategoryId(Long id){
        return productServiceBlockingStub.getAllProductsByChildCategoryId(
                        ProductServiceRequest
                                .newBuilder()
                                .setChildCategoryId(id)
                                .build())
                .getProductsList()
                .stream()
                .map(i-> ProductDto.builder().id(i.getId())
                        .name(i.getName())
                        .price(i.getPrice())
                        .imageUrl(i.getImageUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
