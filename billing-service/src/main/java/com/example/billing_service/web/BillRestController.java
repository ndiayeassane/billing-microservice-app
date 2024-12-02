package com.example.billing_service.web;

import com.example.billing_service.entities.Bill;
import com.example.billing_service.entities.ProductItem;
import com.example.billing_service.openfeign.CustomerRestClient;
import com.example.billing_service.openfeign.ProductRestClient;
import com.example.billing_service.repository.BillRepository;
import com.example.billing_service.repository.ProductItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BillRestController {

    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;

    private CustomerRestClient customerRestClient;
    private ProductRestClient productRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBill(@PathVariable Long id){

        Bill bill  = billRepository.findById(id).get();

        bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));

        bill.getProductItems().forEach(
                productItem -> {
                    productItem.setProduct(
                            productRestClient.getProductById(productItem.getProductId())
                    );
                }
        );

        return bill;


    }
}
