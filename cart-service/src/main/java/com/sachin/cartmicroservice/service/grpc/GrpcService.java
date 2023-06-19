package com.sachin.cartmicroservice.service.grpc;


import com.sachin.cartmicroservice.dto.ProductDto;
import com.sachin.ecommerce_microservice_project.product.GetProductsRequest;
import com.sachin.ecommerce_microservice_project.product.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GrpcService {
    @GrpcClient("product-service")
    private ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public List<ProductDto> getAllProductsIn(List<Long> productIds){
        return productServiceBlockingStub.getAllProducts(GetProductsRequest.newBuilder()
                        .addAllProductId(productIds)
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
