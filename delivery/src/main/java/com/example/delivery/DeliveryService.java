package com.example.delivery;

import com.example.delivery.model.OrderDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
public class DeliveryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryService.class);
    private static final String RESTAURANT_ORDER_DELIVERED_NOTIFICATION = "http://localhost:8082/restaurant/status/";

    @Autowired
    private RestTemplate restTemplate;

    public Mono<Void> receiveDetails(OrderDetails orderDetails) {
        return Mono.just(orderDetails)
                .map(OrderDetails::getId)
                .map(id -> RESTAURANT_ORDER_DELIVERED_NOTIFICATION + id)
                .map(temporaryPath -> restTemplate.getForEntity(temporaryPath, String.class))
                .doOnNext(response -> LOGGER.info("Restaurant response: {}", response.getBody()))
                .then();
    }
}
