package com.sachin.ordermicroservice;


import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class);
    }

    @Bean
    public RazorpayClient getRazorPayClient() throws RazorpayException {
        return new RazorpayClient("rzp_test_8ZxKiN0ZpqMvLd","qTrl8lFwUKt6GkV6JZbY75Zc");
    }

}
