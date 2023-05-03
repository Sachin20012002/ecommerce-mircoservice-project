package com.codingmart.productmicroservice;

import com.codingmart.productmicroservice.entity.Product;
import com.codingmart.productmicroservice.entity.Type;
import com.codingmart.productmicroservice.service.kafka.KafkaService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductServiceApplicationTests {

	@Autowired
	KafkaService kafkaService;

	@SneakyThrows
	@Test
	void contextLoads() {
		kafkaService.addProductEvent(Product.builder()
						.id(1L)
						.name("shirt")
						.color("red")
						.type(new Type())
						.quantity(10L)
				.build());
	}

}
