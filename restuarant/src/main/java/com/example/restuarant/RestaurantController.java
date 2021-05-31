package com.example.restuarant;

import com.example.restuarant.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestaurantController.class);
    public static final String ORDER_COMPLETED = "ORDER COMPLETED";

    @Autowired
    private RestaurantOrderService orderService;

    @PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
    private Mono<Void> createOrder(@RequestBody Order order) {
        return Mono.just(order)
                .doOnNext(orderReceived -> LOGGER.info("Processing new order: {} ...", orderReceived))
                .flatMap(orderService::receiveOrder);
    }

    @GetMapping(path = "/status/{id}")
    private Mono<String> receiveOrderStatus(@PathVariable String id) {
        return Mono.just(id)
                .doOnNext(orderId -> LOGGER.info("Getting order status for order with id {} ...", orderId))
                .thenReturn(ORDER_COMPLETED);
    }
}
