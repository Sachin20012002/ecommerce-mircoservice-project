package com.codingmart.productmicroservice.controller;


import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.response.GenericResponse;
import com.codingmart.productmicroservice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;


    @PostMapping(value = "",consumes = "application/json")
    public GenericResponse<Image> addImage(@Valid @RequestBody Image image){
        return GenericResponse.<Image>builder()
            .code(201)
            .status(HttpStatus.CREATED)
            .data(imageService.addImage(image))
            .build();
    }

    @GetMapping("")
    public GenericResponse<List<Image>> getAllImages(){
        return GenericResponse.<List<Image>>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(imageService.getAllImages())
                .build();
    }

    @GetMapping("/{id}")
    public GenericResponse<Image> getImage(@PathVariable("id") Long id){
        return GenericResponse.<Image>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(imageService.getImage(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public GenericResponse<Response> deleteImage(@PathVariable("id") Long id){
        return GenericResponse.<Response>builder()
                .code(204)
                .status(HttpStatus.CREATED)
                .data(imageService.deleteImage(id))
                .build();
    }

    @PutMapping(value = "/{id}",consumes = "application/json")
    public GenericResponse<Image> updateImage(@Valid @RequestBody Image image, @PathVariable("id") Long id){
        return GenericResponse.<Image>builder()
                .code(200)
                .status(HttpStatus.CREATED)
                .data(imageService.updateImage(image,id))
                .build();

    }
}
