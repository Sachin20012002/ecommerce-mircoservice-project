package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;
    private final GenericResponse genericResponse;

    @Autowired
    public TypeController(TypeService typeService, GenericResponse genericResponse){
        this.typeService=typeService;
        this.genericResponse = genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addType(@RequestBody Type type){
        genericResponse.setData(typeService.addType(type));
        return genericResponse;
    }

    @GetMapping("")
    public GenericResponse getAllTypes(){
        genericResponse.setData(typeService.getAllTypes());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getType(@PathVariable("id") Long id){
        genericResponse.setData(typeService.getType(id));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteType(@PathVariable("id") Long id){
        genericResponse.setData(typeService.deleteType(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateType(@Valid @RequestBody Type type, @PathVariable("id") Long id){
        genericResponse.setData(typeService.updateType(type,id));
        return genericResponse;
    }

}
