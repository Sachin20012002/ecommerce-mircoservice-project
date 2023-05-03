package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Tax;
import com.codingmart.productmicroservice.service.TaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/taxes")
public class TaxController {

    private final TaxService taxService;
    private final GenericResponse genericResponse;

    @Autowired
    public TaxController(TaxService taxService, GenericResponse genericResponse){
        this.taxService=taxService;
        this.genericResponse = genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addTax(@RequestBody Tax tax){

        genericResponse.setData(taxService.addTax(tax));
        return genericResponse;
    }


    @GetMapping("")
    public GenericResponse getAllTaxes(){
        genericResponse.setData(taxService.getAllTaxes());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getTax(@PathVariable("id") Long id){
        genericResponse.setData(taxService.getTax(id));
        return genericResponse;

    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteTax(@PathVariable("id") Long id){
        genericResponse.setData(taxService.deleteTax(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateTax(@Valid @RequestBody Tax tax, @PathVariable("id") Long id){
        genericResponse.setData(taxService.updateTax(tax,id));
        return genericResponse;
    }

}
