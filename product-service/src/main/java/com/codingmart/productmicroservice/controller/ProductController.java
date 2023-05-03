package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.service.ProductService;
import com.codingmart.productmicroservice.service.kafka.KafkaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final KafkaService kafkaService;
    private final GenericResponse genericResponse;


    @GetMapping("/{id}")
    public GenericResponse getProductById(@PathVariable("id") Long id){
        genericResponse.setData(productService.getProductById(id));
        return genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addProduct(@Valid @RequestBody Product product){
        genericResponse.setData(productService.addProduct(product));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteProduct(@PathVariable("id") Long id){
        genericResponse.setData(productService.deleteProduct(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateProduct(@PathVariable("id") Long id,@Valid @RequestBody Product product){
        genericResponse.setData(productService.updateProduct(id,product));
        return genericResponse;
    }

     @GetMapping("/child-category/{id}")
     public GenericResponse getAllProductsByChildCategoryId(@PathVariable("id") Long id){
         genericResponse.setData(productService.getAllProductsByChildCategoryId(id));
         return genericResponse;
     }
    @GetMapping("/child-category/active/{id}")
    public List<Product> getAllActiveProductsByChildCategoryId(@PathVariable("id") Long id){
        return productService.getAllActiveProductsByChildCategoryId(id);
    }
    @GetMapping("")
    public GenericResponse getAllProducts(){
        genericResponse.setData(productService.getAllProducts());
        return genericResponse;
    }
    @GetMapping("/active")
    public GenericResponse getAllActiveProducts(){
        genericResponse.setData(productService.getAllActiveProducts());
        return genericResponse;
    }

   @GetMapping("/filter")
    public List<Product> getAllActiveProductsForFilterService(){
       return productService.getAllActiveProducts();
   }

   @GetMapping("/test")
   public void test() throws JsonProcessingException {
       kafkaService.addProductEvent(Product.builder()
               .id(1L)
               .name("shirt")
               .color("red")
               .type(new Type())
               .quantity(10L)
               .build());
   }




}
