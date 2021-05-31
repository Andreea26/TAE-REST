package com.example.restuarant;

import com.example.restuarant.model.Order;
import com.example.restuarant.model.OrderDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@Service
public class RestaurantOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantOrderService.class);
    private static final String DELIVERY_SEND_ORDER = "http://localhost:8083/delivery/details";

    @Autowired
    private RestTemplate restTemplate;

    public Mono<Void> receiveOrder(Order order) {
        return Mono.just(order)
                .map(OrderDetails::fromOrder)
                .doOnNext(orderDetails -> LOGGER.info("Order with id {}  wad processed. Sending order details {}  to delivery ..",
                        order.getId(), order.getDetails()))
                .map(orderDetails -> restTemplate.postForEntity(DELIVERY_SEND_ORDER, orderDetails, OrderDetails.class))
                .then();
    }

}
