package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Discount;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface DiscountService {
    Discount addDiscount(Discount discount);

    List<Discount> getAllDiscounts();

    Discount getDiscountById(Long id);

    Response deleteDiscount(Long id);

    Discount updateDiscount(Discount discount,Long id);

}
