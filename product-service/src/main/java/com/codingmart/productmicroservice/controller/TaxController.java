package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Tax;
import com.codingmart.productmicroservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/taxes")
public class TaxController {

    private final TaxService taxService;


    @Autowired
    public TaxController(TaxService taxService){
        this.taxService=taxService;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Tax> addTax(@RequestBody Tax tax){
        return GenericResponse.<Tax>builder()
                .code(201)
                .data(taxService.addTax(tax)).build();
    }


    @GetMapping("")
    public GenericResponse<List<Tax>> getAllTaxes(){
        return GenericResponse.<List<Tax>>builder()
                .code(200)
                .data(taxService.getAllTaxes()).build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Tax> getTax(@PathVariable("id") Long id){
        return GenericResponse.<Tax>builder()
                .code(200)
                .data(taxService.getTax(id)).build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteTax(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(200)
                .data(taxService.deleteTax(id)).build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Tax> updateTax(@Valid @RequestBody Tax tax, @PathVariable("id") Long id){
        return GenericResponse.<Tax>builder()
                .code(200)
                .data(taxService.updateTax(tax,id)).build();
    }

}
