package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Discount;
import com.codingmart.productmicroservice.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/discounts")
public class DiscountController {

    private final DiscountService discountService;
    private final GenericResponse genericResponse;

    @Autowired
    public DiscountController(DiscountService discountService, GenericResponse genericResponse){
        this.discountService=discountService;
        this.genericResponse = genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addDiscount(@Valid @RequestBody Discount discount){
        genericResponse.setData(discountService.addDiscount(discount));
        return genericResponse;
    }

    @GetMapping("")
    public GenericResponse getAllDiscounts(){
        genericResponse.setData(discountService.getAllDiscounts());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getDiscount(@PathVariable("id") Long id){
        genericResponse.setData(discountService.getDiscountById(id));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteDiscount(@PathVariable("id") Long id){
        genericResponse.setData(discountService.deleteDiscount(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateDiscount(@Valid @RequestBody Discount discount,@PathVariable("id") Long id){
        genericResponse.setData(discountService.updateDiscount(discount,id));
        return genericResponse;
    }
}
