package com.example.billing_service.entities;

import com.example.billing_service.models.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    private Long customerId;
    @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems = new ArrayList<>();
    @Transient
    private Customer customer;
}
