package com.sachin.cartmicroservice.service;

import com.sachin.cartmicroservice.model.CartItem;
import com.sachin.cartmicroservice.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService{

    private final CartItemRepository cartItemRepository;
    @Override
    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void delete(String cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    @Override
    public CartItem update(String cartItemId, CartItem cartItem) {
        cartItem.setId(cartItemId);
        return cartItemRepository.save(cartItem);
    }
}
