package com.example.restuarant.model;

import lombok.Data;

@Data
public class Order {

    private String id;
    private Integer products;
    private String details;
    private Double price;

    @Override
    public String toString() {
        return String.format("id: '%s', products: '%d', details: '%s', price: '%f'", id, products, details, price);
    }
}
