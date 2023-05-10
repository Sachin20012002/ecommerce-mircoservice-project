package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CartItem;

public interface CartItemService {
    CartItem save(CartItem cartItem);

    void delete(String cartItemId);

    CartItem update(String cartItemId, CartItem cartItem);
}
