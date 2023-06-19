package com.sachin.cartmicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.cartmicroservice.dto.CreateCartMessage;
import com.sachin.cartmicroservice.model.Cart;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final KafkaTemplate<String,String> kafkaTemplate;
    private final CartService cartService;
    @SneakyThrows
    @KafkaListener(
            topics = "CREATE_CART",
            groupId = "myGroup-1"
    )
    public void consume(String customerId){
        Cart cart=cartService.initialiseCartToCustomer(customerId);
        updateCustomerCart(customerId,cart.getId());
    }

    public void updateCustomerCart(String customerId,String cartId) throws JsonProcessingException {
        CreateCartMessage createCartMessage=new CreateCartMessage(customerId,cartId);
        ObjectMapper objectMapper=new ObjectMapper();
        String message=objectMapper.writeValueAsString(createCartMessage);
        kafkaTemplate.send("CART-CREATED", message);
    }


}
