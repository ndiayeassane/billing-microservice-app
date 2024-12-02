package com.example.customer_service;

import com.example.customer_service.config.CustomerConfigParams;
import com.example.customer_service.entities.Customer;
import com.example.customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
	return args -> {
		customerRepository.save(
				Customer.builder()
						.name("Azou1")
						.email("azou1@mail.com")
						.build()
		);
		customerRepository.save(
				Customer.builder()
						.name("Azou2")
						.email("azou2@mail.com")
						.build()
		);
		customerRepository.save(
				Customer.builder()
						.name("Azou3")
						.email("azou3@mail.com")
						.build()
		);

		customerRepository.findAll().forEach(
				list->{
					System.out.println("============================");
					System.out.println("name : "+list.getName());
					System.out.println("email : "+list.getEmail());
					System.out.println("============================");
				}
		);
	};
	}
}
