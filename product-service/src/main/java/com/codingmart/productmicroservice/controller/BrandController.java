package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.entity.Brand;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/brands",produces = "application/json")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;


    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Brand> addBrand(@Valid @RequestBody Brand brand){
        return GenericResponse.<Brand>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(brandService.addBrand(brand))
                .build();
    }

    @GetMapping("")
    public GenericResponse<List<Brand>> getAllBrands(){
        return GenericResponse.<List<Brand>>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(brandService.getAllBrands())
                .build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Brand> getBrand(@PathVariable("id") Long id){
        return GenericResponse.<Brand>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(brandService.getBrand(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteBrand(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(204)
                .status(HttpStatus.OK)
                .data(brandService.deleteBrand(id))
                .build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Brand> updateBrand(@Valid @RequestBody Brand brand,@PathVariable("id") Long id){
        return GenericResponse.<Brand>builder()
                .code(204)
                .status(HttpStatus.OK)
                .data(brandService.updateBrand(brand,id))
                .build();
    }
}
