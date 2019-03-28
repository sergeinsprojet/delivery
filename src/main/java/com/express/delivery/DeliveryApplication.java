package com.express.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.express.delivery.repository")
public class DeliveryApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}
}
