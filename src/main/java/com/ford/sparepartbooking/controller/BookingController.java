package com.ford.sparepartbooking.controller;

import com.ford.sparepartbooking.models.BookingRequest;
import com.ford.sparepartbooking.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("")
    public ResponseEntity<Object> bookSpareParts(@RequestBody BookingRequest request) {
        long orderId = bookingService.bookSpareParts(request);

        Map<Object, Object> response = new HashMap<>();
        response.put("message", "Order created successfully with orderId " + orderId);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
