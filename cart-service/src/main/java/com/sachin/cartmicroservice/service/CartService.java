package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CartItem;

public interface CartService {
    Cart addProductToCart(CartItem cartItem, String cartId);

    Cart initialiseCartToCustomer(String customerId);


    Cart getCartByCustomerToken(String token);
}
