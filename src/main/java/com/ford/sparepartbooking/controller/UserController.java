package com.ford.sparepartbooking.controller;

import com.ford.sparepartbooking.models.order.OrderDTO;
import com.ford.sparepartbooking.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<Object> getOrders(@PathVariable("userId") long userId) {
        Iterable<OrderDTO> orders = userService.getOrders(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

}
