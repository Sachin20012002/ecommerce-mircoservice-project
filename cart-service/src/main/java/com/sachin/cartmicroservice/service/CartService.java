package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CartItem;

import java.util.List;

public interface CartService {
    Cart addProductToCart(CartItem cartItem, String cartId);

    Cart initialiseCartToCustomer(String customerId);


    Cart getCartByCustomerToken(String token);

    List<Cart> getAllCarts();

    Cart getCartById(String cartId);
}
