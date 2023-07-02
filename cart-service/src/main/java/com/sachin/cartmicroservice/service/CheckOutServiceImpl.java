package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.enums.PaymentType;
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
    private final PaymentService paymentService;

   public CheckOut create(){
       return checkOutRepository.save(CheckOut.builder().build());
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

    @Override
    public CheckOut updatePaymentType(String checkOutId, PaymentType paymentType) {
        CheckOut checkOut=getById(checkOutId);
        checkOut.setPayment(paymentService.createPayment(paymentType));
        return checkOutRepository.save(checkOut);
    }

    @Override
    public CheckOut getCheckOutForCart(String cartId) {
        Cart cart=cartService.getCartById(cartId);
        if(Objects.isNull(cart.getCheckOut())){
            cart.setCheckOut(create());
            cart=cartService.save(cart);
        }
        return cart.getCheckOut();
    }

    public CheckOut getById(String id){
        return checkOutRepository.findById(id)
                        .orElseThrow(()-> new NotFoundException("CheckOut id is not found"));
    }




}
