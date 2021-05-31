package com.example.delivery.model;

import lombok.Data;

@Data
public class OrderDetails {

    private String id;
    private String details;

    @Override
    public String toString() {
        return String.format("id: '%s', details: '%s'", id, details);
    }
}
