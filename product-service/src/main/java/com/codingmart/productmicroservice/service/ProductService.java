package com.codingmart.productmicroservice.service;


import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    Product addProduct(Product product);

    Response deleteProduct(Long id);

    Product updateProduct(Long id, Product product);

    List<Product> getAllProductsByChildCategoryId(Long id);

    List<Product> getAllProductsByTypeId(Long id);

    List<Product> getAllProductsByBrandId(Long id);

    List<Product> getAllProducts();

    List<Product> getAllProductsByColor(String color);

    List<Product> getAllActiveProducts();

    List<Product> getAllActiveProductsByChildCategoryId(Long id);
}
