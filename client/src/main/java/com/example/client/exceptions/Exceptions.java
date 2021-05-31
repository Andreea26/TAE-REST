package com.example.client.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class Exceptions {

    public static ResponseStatusException ORDER_NOT_CREATED = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
            "Order couldn't be created");
}
