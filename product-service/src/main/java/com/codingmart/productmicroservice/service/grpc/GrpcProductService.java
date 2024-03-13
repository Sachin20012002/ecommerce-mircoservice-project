package com.codingmart.productmicroservice.service.grpc;


import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.service.ProductService;
import com.sachin.ecommerce_microservice_project.product.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@RequiredArgsConstructor
public class GrpcProductService extends ProductServiceGrpc.ProductServiceImplBase {

    private final ProductService productService;
    @Override
    public void getAllProductsByChildCategoryId(ProductServiceRequest request, StreamObserver<ProductServiceResponse> responseObserver) {
        System.out.println("REQUEST REACHED");
        ProductServiceResponse.Builder builder=ProductServiceResponse.newBuilder();
        List<Product> products=productService.getAllProductsByChildCategoryId(request.getChildCategoryId());
        System.out.println(products.size()+"GOT PRODUCTS FROM DATABASE");
        System.out.println(products.get(0).getImages());
        List<ProductGrpc> productGrpcList=products.stream().map(product ->
                ProductGrpc.newBuilder()
                        .setId(product.getId())
                        .setName(product.getName())
                        .setPrice(product.getFinalDiscountedPrice())
                        .setImageUrl(product.getImages().get(0).getUrl()).build())
                        .collect(Collectors.toList());
        builder.addAllProducts(productGrpcList);
        System.out.println("PRODUCTS READY FOR GRPC");
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getAllProducts(GetProductsRequest request, StreamObserver<ProductServiceResponse> responseObserver) {
        ProductServiceResponse.Builder builder=ProductServiceResponse.newBuilder();
        List<Product> products=productService.findByIdIn(request.getProductIdList());
        List<ProductGrpc> productGrpcList=products.stream().map(product ->
                        ProductGrpc.newBuilder()
                                .setId(product.getId())
                                .setName(product.getName())
                                .setPrice(product.getFinalDiscountedPrice())
                                .setImageUrl(product.getImages().get(0).getUrl()).build())
                .toList();
        builder.addAllProducts(productGrpcList);
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}
