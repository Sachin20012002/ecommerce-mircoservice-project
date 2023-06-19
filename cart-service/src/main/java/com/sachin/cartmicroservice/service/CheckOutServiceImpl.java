package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.exception.NotFoundException;
import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CheckOut;
import com.sachin.cartmicroservice.repository.CheckOutRepository;
import com.sachin.cartmicroservice.response.SummaryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CheckOutServiceImpl implements CheckOutService{

    private final CheckOutRepository checkOutRepository;
    private final CartService cartService;
    @Override
    public CheckOut create(String cartId) {
        Cart cart=cartService.getCartById(cartId);
        if(Objects.isNull(cart.getCheckOut())) return checkOutRepository.save(CheckOut.builder()
                .cart(cart).build());
        return cart.getCheckOut();
    }

    @Override
    public CheckOut updateShippingAddress(String checkOutId, String shippingAddressId) {
        CheckOut checkOut=getById(checkOutId);
        checkOut.setShippingAddressId(shippingAddressId);
        return checkOutRepository.save(checkOut);
    }

    @Override
    public SummaryResponse getCheckOutSummary(String checkOutId) {
        return null;
    }

    public CheckOut getById(String id){
        return checkOutRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("CheckOut id is not found"));
    }




}
