package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.exception.NotFoundException;
import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CartItem;
import com.sachin.cartmicroservice.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

   private final CartRepository cartRepository;
   private final CartItemService cartItemService;
   private final JwtService jwtService;
   @Override
   public Cart addProductToCart(CartItem cartItem,String cartId) {
         Cart cart=cartRepository.findById(cartId).orElseThrow(()-> new NotFoundException("CartId not found"));
         cart.getCartItems().add(cartItemService.save(cartItem));
         return cartRepository.save(cart);
   }

    @Override
    public Cart initialiseCartToCustomer(String customerId) {
        return cartRepository.findById(customerId).orElse(cartRepository.save(Cart.builder()
                .customerId(customerId)
                .cartItems(new ArrayList<>())
                .build()));
    }

    @Override
    public Cart getCartByCustomerToken(String token) {
        String customerId=jwtService.getUserIdFromToken(token);
        return cartRepository.findByCustomerId(customerId).orElse(initialiseCartToCustomer(customerId));
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart getCartById(String cartId) {
        return cartRepository.findById(cartId).orElseThrow(()-> new NotFoundException("cartId not found"));
    }


}
