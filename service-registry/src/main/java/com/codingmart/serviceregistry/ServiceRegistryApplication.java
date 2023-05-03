package com.codingmart.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServiceRegistryApplication {

	/**
	 *  A service registry is useful because it enables client-side load-balancing
	 *  and decouples service providers from consumers without the need for DNS.
	 */
	public static void main(String[] args) {

		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
