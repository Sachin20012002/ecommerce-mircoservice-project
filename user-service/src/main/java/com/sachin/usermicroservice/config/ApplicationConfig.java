package com.sachin.usermicroservice.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

   public static final String[] AUTH_WHITELIST= {
           "/signup",
           "/login"
   };

}
