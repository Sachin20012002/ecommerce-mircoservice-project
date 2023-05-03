package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductDetailRepository extends JpaRepository<ProductDetail,Long> {

    @Modifying
    @Query(value = "delete from product_detail where id=:id",nativeQuery = true)
    void deleteById(Long id);

}
