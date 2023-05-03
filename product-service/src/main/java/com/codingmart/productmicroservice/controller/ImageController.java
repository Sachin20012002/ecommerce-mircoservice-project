package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;
    private final GenericResponse genericResponse;

    @Autowired
    public ImageController(ImageService imageService, GenericResponse genericResponse){
        this.imageService=imageService;
        this.genericResponse = genericResponse;
    }

    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse addImage(@Valid @RequestBody Image image){
        genericResponse.setData(imageService.addImage(image));
        return genericResponse;
    }

    @GetMapping("")
    public GenericResponse getAllImages(){
        genericResponse.setData(imageService.getAllImages());
        return genericResponse;
    }

    @GetMapping("/{id}")
    public GenericResponse getImage(@PathVariable("id") Long id){
        genericResponse.setData(imageService.getImage(id));
        return genericResponse;
    }

    @DeleteMapping("/{id}")
    public GenericResponse deleteImage(@PathVariable("id") Long id){
        genericResponse.setData(imageService.deleteImage(id));
        return genericResponse;
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse updateImage(@Valid @RequestBody Image image, @PathVariable("id") Long id){
        genericResponse.setData(imageService.updateImage(image,id));
        return genericResponse;
    }
}
