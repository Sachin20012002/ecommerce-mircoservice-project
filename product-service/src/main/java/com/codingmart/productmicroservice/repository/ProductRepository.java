package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByChildCategoryId(Long id);

    List<Product> findAllByTypeId(Long id);

    List<Product> findAllByBrandId(Long id);

    List<Product> findAllByColor(String color);

    List<Product> findAllByActive(boolean b);

    List<Product> findAllByActiveAndChildCategoryId(boolean b, Long id);

    List<Product> findByIdIn(List<Long> productIdList);
}
