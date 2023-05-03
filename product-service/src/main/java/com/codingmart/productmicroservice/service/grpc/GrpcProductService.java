package com.codingmart.productmicroservice.service.grpc;


import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.service.ProductService;
import com.sachin.ecommerce_microservice_project.product.ProductGrpc;
import com.sachin.ecommerce_microservice_project.product.ProductServiceGrpc;
import com.sachin.ecommerce_microservice_project.product.ProductServiceRequest;
import com.sachin.ecommerce_microservice_project.product.ProductServiceResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
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
}
