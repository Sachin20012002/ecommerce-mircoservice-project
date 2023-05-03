package com.sachin.filterservice.repository;

import com.sachin.filterservice.model.ProductES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductESRepository extends ElasticsearchRepository<ProductES,Long> {

    List<ProductES> findAllByBrandName(String brandName);

    List<ProductES> findAllByColor(String color);

    List<ProductES> findAllByTypeName(String typeName);
}
