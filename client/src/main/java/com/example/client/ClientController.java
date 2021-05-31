package com.example.client;

import com.example.client.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientOrderService orderService;

    @PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
    private Mono<Order> createOrder(@RequestBody Order order) {
        return Mono.just(order)
                .doOnNext(orderService::createOrder);
    }
}
