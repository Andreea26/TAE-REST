package com.example.client;

import com.example.client.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class ClientOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientOrderService.class);
    private static final String RESTAURANT_SEND_ORDER = "http://localhost:8082/restaurant/order";

    @Autowired
    private RestTemplate restTemplate;

    public void createOrder(Order order) {
        order.setId(UUID.randomUUID().toString());

        restTemplate.postForEntity(RESTAURANT_SEND_ORDER, order, Order.class);

        LOGGER.info("New order created: {}", order);
    }
}
