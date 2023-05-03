package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {

    Brand findByName(String name);
}
