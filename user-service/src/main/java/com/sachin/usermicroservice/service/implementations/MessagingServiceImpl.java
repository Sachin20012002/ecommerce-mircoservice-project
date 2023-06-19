package com.sachin.usermicroservice.service.implementations;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.usermicroservice.dto.CreateCartResponse;
import com.sachin.usermicroservice.service.CustomerService;
import com.sachin.usermicroservice.service.MessagingService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingServiceImpl implements MessagingService {




    @Autowired
    private CustomerService customerService;

    private final KafkaTemplate<String,String> kafkaTemplate;

    public void createCartForCustomer(String customerId) {
        kafkaTemplate.send("CREATE_CART",customerId);
    }


    @SneakyThrows
    @KafkaListener(
            topics = "CART-CREATED",
            groupId = "myGroup-2"
    )
    public void consumeCreateCartResponse(String createCartResponseMessage){
        ObjectMapper objectMapper=new ObjectMapper();
        CreateCartResponse createCartResponse=objectMapper.readValue(createCartResponseMessage, CreateCartResponse.class);
        String customerId= createCartResponse.getCustomerId();
        String cartId=createCartResponse.getCartId();
        customerService.updateCustomerCartId(customerId,cartId);
    }
}
