package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Brand;
import com.codingmart.productmicroservice.entity.BrandCode;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandServiceImpl implements BrandService{

    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository){
        this.brandRepository=brandRepository;
    }
    @Override
    public Brand addBrand(Brand brand) {
        brand.setCode(new BrandCode());
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrand(Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        return brandRepository.findById(id).get();
    }

    @Override
    public Response deleteBrand(Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        brandRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public Brand updateBrand(Brand brand,Long id) {
        if(brandRepository.findById(id).isEmpty()){
            throw new NotFoundException("Brand Id not Found");
        }
        Brand existingBrand=brandRepository.findById(id).get();
        existingBrand.setName(brand.getName());
        existingBrand.setActive(brand.getActive());
        return brandRepository.save(existingBrand);
    }
}
