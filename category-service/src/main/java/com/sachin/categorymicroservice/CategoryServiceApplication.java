package com.sachin.categorymicroservice;

import com.sachin.categorymicroservice.audit.AuditableAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class CategoryServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(CategoryServiceApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware(){

		return new AuditableAware();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}
