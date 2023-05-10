package com.sachin.cartmicroservice.controller;


import com.sachin.cartmicroservice.model.CartItem;
import com.sachin.cartmicroservice.response.GenericResponse;
import com.sachin.cartmicroservice.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cartItems")
public class CartItemController {
    private final CartItemService cartItemService;

    @DeleteMapping("/{id}")
    public GenericResponse<?> deleteCartItem(@PathVariable("id") String cartItemId){
        cartItemService.delete(cartItemId);
        return GenericResponse.builder()
                .code(204)
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @PutMapping("/{id}")
    public GenericResponse<CartItem> updateCartItem(@PathVariable("id") String cartItemId,@RequestBody CartItem cartItem){
        return GenericResponse.<CartItem>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(cartItemService.update(cartItemId,cartItem)).build();
    }
}
