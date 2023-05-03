package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface ImageService {
    Image updateImage(Image image,Long id);

    Response deleteImage(Long id);

    Image getImage(Long id);

    List<Image> getAllImages();

    Image addImage(Image image);
}
