package com.sachin.cartmicroservice.controller;

import com.sachin.cartmicroservice.enums.PaymentType;
import com.sachin.cartmicroservice.model.CheckOut;
import com.sachin.cartmicroservice.model.Payment;
import com.sachin.cartmicroservice.response.GenericResponse;
import com.sachin.cartmicroservice.response.SummaryResponse;
import com.sachin.cartmicroservice.service.CheckOutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckOutController {
    private final CheckOutService checkOutService;

    @GetMapping("/{cartId}")
    public GenericResponse<CheckOut> getCheckOutForCart(@PathVariable String cartId){
        return GenericResponse.<CheckOut>builder().code(201)
                .status(HttpStatus.CREATED)
                .data(checkOutService.getCheckOutForCart(cartId))
                .build();
    }

    @PatchMapping("/{checkOutId}/address/{shippingAddressId}")
    public GenericResponse<CheckOut> updateShippingAddress(@PathVariable String checkOutId, @PathVariable String shippingAddressId){
        return GenericResponse.<CheckOut>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(checkOutService.updateShippingAddress(checkOutId,shippingAddressId))
                .build();
    }

    @PatchMapping("/{checkOutId}/payment/{paymentType}")
    public GenericResponse<CheckOut> updatePaymentType(@PathVariable String checkOutId, @PathVariable PaymentType paymentType){
        return GenericResponse.<CheckOut>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(checkOutService.updatePaymentType(checkOutId,paymentType))
                .build();
    }


    @GetMapping("/{checkOutId}/summary")
    public GenericResponse<SummaryResponse> getCheckOutSummary(@PathVariable String checkOutId){
        return GenericResponse.<SummaryResponse>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(checkOutService.getCheckOutSummary(checkOutId))
                .build();
    }
}
