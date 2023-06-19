package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.model.CheckOut;
import com.sachin.cartmicroservice.response.SummaryResponse;

public interface CheckOutService {

    CheckOut create(String cartId);

    CheckOut updateShippingAddress(String checkOutId, String shippingAddressId);

    SummaryResponse getCheckOutSummary(String checkOutId);
}
