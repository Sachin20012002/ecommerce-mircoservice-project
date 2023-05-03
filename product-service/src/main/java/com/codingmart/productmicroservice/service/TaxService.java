package com.codingmart.productmicroservice.service;

import com.codingmart.productmicroservice.entity.Tax;
import com.codingmart.productmicroservice.enums.Response;

import java.util.List;

public interface TaxService {
    Tax addTax(Tax tax);

    Tax getTaxByName(String name);

    Tax updateTax(Tax tax,Long id);

    Tax getTax(Long id);

    Response deleteTax(Long id);

    List<Tax> getAllTaxes();
}
