package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.entity.Size;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sizes")
public class SizeController {

    private final SizeService sizeService;


    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Size> addSize(@Valid @RequestBody Size size){
        return GenericResponse.<Size>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(sizeService.addSize(size))
                .build();
    }

    @GetMapping("")
    public GenericResponse<List<Size>> getAllSizes(){
        return GenericResponse.<List<Size>>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(sizeService.getAllSizes())
                .build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Size> getSize(@PathVariable("id") Long id){
        return GenericResponse.<Size>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(sizeService.getSize(id))
                .build();

    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteSize(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(204)
                .status(HttpStatus.NO_CONTENT)
                .data(sizeService.deleteSize(id))
                .build();

    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Size> updateSize(@Valid @RequestBody Size size, @PathVariable("id") Long id){
        return GenericResponse.<Size>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(sizeService.updateSize(size,id))
                .build();

    }
}
