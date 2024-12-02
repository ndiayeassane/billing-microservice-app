package com.example.billing_service;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.models.Customer;
import com.example.billing_service.models.Product;
import com.example.billing_service.openfeign.CustomerRestClient;
import com.example.billing_service.openfeign.ProductRestClient;
import com.example.billing_service.repository.BillRepository;
import com.example.billing_service.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BillRepository billRepository,
										ProductItemRepository productItemRepository,
										CustomerRestClient customerRestClient,
										ProductRestClient productRestClient
	){
		return args -> {

			Collection<Customer> customers = customerRestClient.getAllCustomers().getContent();
			Collection<Product> products = productRestClient.getAllProducts().getContent();

			customers.forEach(
					customer -> {

						Bill bill = Bill.builder()
								.customerId(customer.getId())
								.billingDate(new Date())
								.build();
						billRepository.save(bill);

						products.forEach(
								product -> {
									ProductItem productItem = ProductItem.builder()
											.quantity(1 + new Random().nextInt(10))
											.productId(product.getId())
											.priceItem(product.getPrice())
											.bill(bill)
											.build();

									productItemRepository.save(productItem);
								}
						);
					}
			);



		};
	}
}
