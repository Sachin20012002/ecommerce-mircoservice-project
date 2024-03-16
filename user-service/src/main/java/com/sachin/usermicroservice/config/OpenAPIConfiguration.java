package com.sachin.usermicroservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {

        Contact myContact = new Contact();
        myContact.setName("Sachin");
        myContact.setEmail("sachin.s.developer@gmail.com");

        Info information = new Info()
                .title("Category Microservice API")
                .version("1.0")
                .description("This API exposes endpoints to manage categories.")
                .contact(myContact);
        return new OpenAPI().info(information);
    }
}