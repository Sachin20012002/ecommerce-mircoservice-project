package com.sachin.cartmicroservice.controller;


import com.sachin.cartmicroservice.model.Cart;
import com.sachin.cartmicroservice.model.CartItem;
import com.sachin.cartmicroservice.response.GenericResponse;
import com.sachin.cartmicroservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping("/addToCart/{cartId}")
    public GenericResponse<Cart> addProductToCart(@RequestBody CartItem cartItem, @PathVariable String cartId){

        return GenericResponse.<Cart>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(cartService.addProductToCart(cartItem,cartId))
                .build();
    }

    @PostMapping("/initialiseCart/{customerId}")
    public GenericResponse<Cart> initialiseCartToCustomer(@PathVariable String customerId){
        return GenericResponse.<Cart>builder()
                .code(201)
                .status(HttpStatus.CREATED)
                .data(cartService.initialiseCartToCustomer(customerId)).build();
    }

    @GetMapping("/token")
    public GenericResponse<Cart> getCartForCustomerByToken(@RequestHeader("Authorization") String authorizationHeader){
        return GenericResponse.<Cart>builder()
                .code(200)
                .status(HttpStatus.OK)
                .data(cartService.getCartByCustomerToken(authorizationHeader.substring(7))).build();
    }
}
