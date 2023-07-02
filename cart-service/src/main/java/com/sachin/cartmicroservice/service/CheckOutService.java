package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.enums.PaymentType;
import com.sachin.cartmicroservice.model.CheckOut;
import com.sachin.cartmicroservice.model.Payment;
import com.sachin.cartmicroservice.response.SummaryResponse;

public interface CheckOutService {


    CheckOut updateShippingAddress(String checkOutId, String shippingAddressId);

    SummaryResponse getCheckOutSummary(String checkOutId);

    CheckOut updatePaymentType(String checkOutId, PaymentType paymentType);

    CheckOut getCheckOutForCart(String cartId);
}
