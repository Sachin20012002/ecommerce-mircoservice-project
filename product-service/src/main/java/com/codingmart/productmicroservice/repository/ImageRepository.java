package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ImageRepository extends JpaRepository<Image,Long> {
    @Modifying
    @Query(value = "delete from image where id=:id",nativeQuery = true)
    void deleteById(Long id);
}
