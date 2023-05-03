package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.entity.TypeCode;
import com.codingmart.productmicroservice.enums.Response;
import com.codingmart.productmicroservice.exception.NotFoundException;
import com.codingmart.productmicroservice.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TypeServiceImpl implements TypeService{

    TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository=typeRepository;
    }
    @Override
    public Type updateType(Type type,Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        Type existingType=typeRepository.findById(id).get();
        existingType.setName(type.getName());
        existingType.setActive(type.getActive());
        typeRepository.save(existingType);
        return existingType;
    }

    @Override
    public Response deleteType(Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        typeRepository.deleteById(id);
        return Response.DELETED;
    }

    @Override
    public Type getType(Long id) {
        if(typeRepository.findById(id).isEmpty()){
            throw new NotFoundException("Type Id not Found");
        }
        return typeRepository.findById(id).get();
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();

    }

    @Override
    public Type addType(Type type) {
        type.setCode(new TypeCode());
        return typeRepository.save(type);
    }



}
