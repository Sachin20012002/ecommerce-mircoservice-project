package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    @GetMapping("/{id}")
    public GenericResponse<Product> getProductById(@PathVariable("id") Long id){
        return GenericResponse.<Product>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(productService.getProductById(id))
                .build();

    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Product> addProduct(@Valid @RequestBody Product product){
        return GenericResponse.<Product>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(productService.addProduct(product))
                .build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteProduct(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(204)
                .status(HttpStatus.NO_CONTENT)
                .data(productService.deleteProduct(id))
                .build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Product> updateProduct(@PathVariable("id") Long id,@Valid @RequestBody Product product){
        return GenericResponse.<Product>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(productService.updateProduct(id,product))
                .build();
    }

     @GetMapping("/child-category/{id}")
     public GenericResponse<List<Product>> getAllProductsByChildCategoryId(@PathVariable("id") Long id){
         return GenericResponse.<List<Product>>builder()
                 .code(200)
                 .status(HttpStatus.OK)
                 .data(productService.getAllProductsByChildCategoryId(id))
                 .build();
     }

    @GetMapping("")
    public GenericResponse<List<Product>> getAllProducts(){
        return GenericResponse.<List<Product>>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(productService.getAllProducts())
                .build();

    }

}
