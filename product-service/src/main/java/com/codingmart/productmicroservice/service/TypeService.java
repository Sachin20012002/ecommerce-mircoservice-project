package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface TypeService {
    Type updateType(Type type,Long id);

    Response deleteType(Long id);

    Type getType(Long id);

    List<Type> getAllTypes();

    Type addType(Type type);


}
