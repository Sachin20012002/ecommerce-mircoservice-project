package com.sachin.filterservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sachin.filterservice.model.ProductES;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaService {

    private final ProductESService productESService;

    @SneakyThrows
    @KafkaListener(
            topics = "POST",
            groupId = "myGroup-1"
    )
    public void consume(String eventMessage){
        System.out.println(eventMessage);
        ObjectMapper objectMapper=new ObjectMapper();
        ProductES product=objectMapper.readValue(eventMessage, ProductES.class);
        System.out.println(product);

        productESService.save(product);
    }
}
