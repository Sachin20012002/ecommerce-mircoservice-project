package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Size;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface SizeService {


    Size addSize(Size size);

    List<Size> getAllSizes();

    Size getSize(Long id);

    Response deleteSize(Long id);

    Size updateSize(Size size,Long id);
}
