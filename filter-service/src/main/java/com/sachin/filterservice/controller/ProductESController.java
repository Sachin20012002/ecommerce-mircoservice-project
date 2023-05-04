package com.sachin.filterservice.controller;

import com.sachin.filterservice.dto.ProductDTO;
import com.sachin.filterservice.response.GenericResponse;
import com.sachin.filterservice.service.ProductESService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductESController {

    private final ProductESService productESService;

    @GetMapping
    public GenericResponse<List<ProductDTO>> getAll(){

        return GenericResponse.<List<ProductDTO>>builder()
                .code(200)
                .data(productESService.getAllProducts()
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList()))
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/brand/{brandName}")
    public GenericResponse<List<ProductDTO>> findByBrandName(@PathVariable String brandName){

        return GenericResponse.<List<ProductDTO>>builder()
                .code(200)
                .data(productESService.findByBrandName(brandName)
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList()))
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/color/{color}")
    public GenericResponse<List<ProductDTO>> findByColor(@PathVariable String color){

        return GenericResponse.<List<ProductDTO>>builder()
                .code(200)
                .data(productESService.findByColor(color)
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList()))
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/type/{typeName}")
    public GenericResponse<List<ProductDTO>> findByTypeName(@PathVariable String typeName){
        return GenericResponse.<List<ProductDTO>>builder()
                .code(200)
                .data(productESService.findByTypeName(typeName)
                        .stream()
                        .map(ProductDTO::new)
                        .collect(Collectors.toList()))
                .status(HttpStatus.OK)
                .build();
    }
}
