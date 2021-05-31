package com.example.delivery;

import com.example.delivery.model.OrderDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    private DeliveryService orderDetailsService;

    @PostMapping(path = "/details", consumes = "application/json", produces = "application/json")
    private Mono<Void> receiveOrderDetails(@RequestBody OrderDetails details) {
        return Mono.just(details)
                .doOnNext(orderDetails -> LOGGER.info("Details received: {}", orderDetails))
                .doOnNext(orderDetails -> LOGGER.info("Order with id {} was delivered. Sending notification to restaurant ...",
                        orderDetails.getId()))
                .flatMap(orderDetailsService::receiveDetails);
    }
}
