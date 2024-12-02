package com.example.inventory_service;

import com.example.inventory_service.entities.Product;
import com.example.inventory_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ProductRepository productRepository){
		return args -> {
			productRepository.save(Product.builder()
							.id(UUID.randomUUID().toString())
							.name("HP")
							.price(200000.00)
							.quantity(10)
					.build());

			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("MAC")
					.price(300000.00)
					.quantity(5)
					.build());

			productRepository.save(Product.builder()
					.id(UUID.randomUUID().toString())
					.name("ACER")
					.price(150000.00)
					.quantity(20)
					.build());
		};
	}
}
