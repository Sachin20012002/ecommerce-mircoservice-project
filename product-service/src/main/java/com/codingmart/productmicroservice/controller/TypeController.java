package com.codingmart.productmicroservice.controller;

import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/types")
public class TypeController {

    private final TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService=typeService;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Type> addType(@RequestBody Type type){
        return GenericResponse.<Type>builder()
                .code(201)
                .data(typeService.addType(type)).build();
    }

    @GetMapping("")
    public GenericResponse<List<Type>> getAllTypes(){
        return GenericResponse.<List<Type>>builder()
                .code(200)
                .data(typeService.getAllTypes()).build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Type> getType(@PathVariable("id") Long id){
        return GenericResponse.<Type>builder()
                .code(200)
                .data(typeService.getType(id)).build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteType(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(200)
                .data(typeService.deleteType(id)).build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Type> updateType(@Valid @RequestBody Type type, @PathVariable("id") Long id){
        return GenericResponse.<Type>builder()
                .code(200)
                .data(typeService.updateType(type,id)).build();
    }

}
