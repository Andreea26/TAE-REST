package com.example.restuarant.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetails {

    private String id;
    private String details;

    @Override
    public String toString() {
        return String.format("id: '%s', details: '%s'", id, details);
    }

    public static OrderDetails fromOrder(Order order) {
        return OrderDetails.builder()
                .id(order.getId())
                .details(order.getDetails())
                .build();
    }
}
