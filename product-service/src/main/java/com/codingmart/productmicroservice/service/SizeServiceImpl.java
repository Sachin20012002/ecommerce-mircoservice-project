package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Size;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.SizeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService{

    SizeRepository sizeRepository;

    public SizeServiceImpl(SizeRepository sizeRepository){
        this.sizeRepository=sizeRepository;
    }
    @Override
    public Size addSize(Size size) {
        return sizeRepository.save(size);
    }



    @Override
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Size getSize(Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        return sizeRepository.findById(id).get();
    }

    @Override
    public Response deleteSize(Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        sizeRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public Size updateSize(Size size,Long id) {
        if(sizeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Size Id not Found");
        }
        Size existingSize=sizeRepository.findById(id).get();
        existingSize.setName(size.getName());
        existingSize.setQuantity(size.getQuantity());
        existingSize.setActive(size.getActive());
        return sizeRepository.save(existingSize);
    }
}
