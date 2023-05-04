package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.entity.Discount;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Discount> addDiscount(@Valid @RequestBody Discount discount){
        return GenericResponse.<Discount>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(discountService.addDiscount(discount))
                .build();
    }

    @GetMapping("")
    public GenericResponse<List<Discount>> getAllDiscounts(){
        return GenericResponse.<List<Discount>>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(discountService.getAllDiscounts())
                .build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Discount> getDiscount(@PathVariable("id") Long id){
        return GenericResponse.<Discount>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(discountService.getDiscountById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteDiscount(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(204)
                .status(HttpStatus.CREATED)
                .data(discountService.deleteDiscount(id))
                .build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Discount> updateDiscount(@Valid @RequestBody Discount discount,@PathVariable("id") Long id){
        return GenericResponse.<Discount>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(discountService.updateDiscount(discount,id))
                .build();
    }
}
