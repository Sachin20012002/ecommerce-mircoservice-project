package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Image;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{

    ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository=imageRepository;
    }
    @Override
    public Image updateImage(Image image,Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        Image existingImage=imageRepository.findById(id).get();
        existingImage.setName(image.getName());
        existingImage.setActive(image.getActive());
        existingImage.setUrl(image.getUrl());
        return imageRepository.save(existingImage);
    }

    @Override
    public Response deleteImage(Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        imageRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public Image getImage(Long id) {
        if(imageRepository.findById(id).isEmpty()){
            throw new NotFoundException("Image Id not Found");
        }
        return imageRepository.findById(id).get();
    }

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image addImage(Image image) {
        return imageRepository.save(image);
    }
}
