package com.sachin.filterservice.service;

import com.sachin.filterservice.model.ProductES;
import com.sachin.filterservice.repository.ProductESRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductESService {
    private final ProductESRepository productESRepository;

    public ProductES save(ProductES productES){
        System.out.println("inside save");
        System.out.println(productESRepository.save(productES));
        System.out.println("saved Successfully");
        return null;
    }

    public List<ProductES> getAllProducts(){
        List<ProductES> productsList = new ArrayList<>();
        productESRepository.findAll().forEach(productsList::add);
        return productsList;
    }

    public List<ProductES> findByBrandName(String brandName) {
        return productESRepository.findAllByBrandName(brandName);
    }

    public List<ProductES> findByColor(String color) {
        return productESRepository.findAllByColor(color);
    }

    public List<ProductES> findByTypeName(String typeName) {
        return productESRepository.findAllByTypeName(typeName);
    }
}
