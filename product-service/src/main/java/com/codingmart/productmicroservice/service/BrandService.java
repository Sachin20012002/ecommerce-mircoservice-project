package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Brand;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface BrandService {
    Brand addBrand(Brand brand);

    List<Brand> getAllBrands();

    Brand getBrand(Long id);

    Response deleteBrand(Long id);

    Brand updateBrand(Brand brand,Long id);

}
