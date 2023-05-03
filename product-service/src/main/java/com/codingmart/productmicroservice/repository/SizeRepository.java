package com.codingmart.productmicroservice.repository;

import com.codingmart.productmicroservice.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SizeRepository extends JpaRepository<Size,Long> {
    @Modifying
    @Query(value = "delete from size where id=:id",nativeQuery = true)
    void deleteById(Long id);
}
