package com.sachin.categorymicroservice.repository;

import com.sachin.categorymicroservice.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
	
	 boolean existsByName(String subCategory);

	Optional<SubCategory> findByName(String name);

	@Query(value = "select * from sub_category where category_id=:id",nativeQuery = true)
	List<SubCategory> findAllByCategoryId(Long id);
}
