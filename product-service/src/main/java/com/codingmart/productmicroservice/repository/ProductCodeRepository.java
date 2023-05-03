package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.ProductCode;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCodeRepository extends JpaRepository<ProductCode,Long> {

    ProductCode findByName(String productCode);
}
