package com.example.billing_service.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private String id;
    private String name;
    private Double price;
    private int quantity;
}
