package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Tax;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxRepository extends JpaRepository<Tax,Long> {
    Tax findByName(String name);
}
