package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Size;
import com.codingmart.productmicroservice.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sizes")
public class SizeController {

    private final SizeService sizeService;
    private final GenericResponse genericResponse;

    @Autowired
    public SizeController(SizeService sizeService, GenericResponse genericResponse){
        this.sizeService=sizeService;
        this.genericResponse = genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addSize(@Valid @RequestBody Size size){
        genericResponse.setData(sizeService.addSize(size));
        return genericResponse;
    }

    @GetMapping("")
    public GenericResponse getAllSizes(){
        genericResponse.setData(sizeService.getAllSizes());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getSize(@PathVariable("id") Long id){
        genericResponse.setData(sizeService.getSize(id));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteSize(@PathVariable("id") Long id){
        genericResponse.setData(sizeService.deleteSize(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateSize(@Valid @RequestBody Size size, @PathVariable("id") Long id){
        genericResponse.setData(sizeService.updateSize(size,id));
        return genericResponse;
    }
}
