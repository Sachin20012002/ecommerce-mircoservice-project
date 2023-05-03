package com.sachin.categorymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sachin.categorymicroservice.entity.ChildCategory;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChildCategoryRepository extends JpaRepository<ChildCategory, Long> {

    
    boolean existsByName(String name);

    Optional<ChildCategory> findByName(String name);

    @Query(value = "select * from child_category where sub_category=:id",nativeQuery = true)
    List<ChildCategory> findAllBySubCategoryId(Long id);
}
