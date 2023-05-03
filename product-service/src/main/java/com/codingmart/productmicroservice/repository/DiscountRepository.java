package com.codingmart.productmicroservice.repository;


import com.codingmart.productmicroservice.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount,Long> {
    Discount findByName(String name);
}
