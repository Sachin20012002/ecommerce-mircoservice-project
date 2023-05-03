package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Brand;
import com.codingmart.productmicroservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/brands",produces = "application/json")
public class BrandController {

    private final BrandService brandService;
    private final GenericResponse genericResponse;

    @Autowired
    public BrandController(BrandService brandService,GenericResponse genericResponse){
        this.brandService=brandService;
        this.genericResponse=genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addBrand(@Valid @RequestBody Brand brand){
        genericResponse.setData(brandService.addBrand(brand));
        return genericResponse;
    }

    @GetMapping("")
    public GenericResponse getAllBrands(){
        genericResponse.setData(brandService.getAllBrands());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getBrand(@PathVariable("id") Long id){
        genericResponse.setData(brandService.getBrand(id));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteBrand(@PathVariable("id") Long id){
        genericResponse.setData(brandService.deleteBrand(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateBrand(@Valid @RequestBody Brand brand,@PathVariable("id") Long id){
        genericResponse.setData(brandService.updateBrand(brand,id));
        return genericResponse;
    }
}
