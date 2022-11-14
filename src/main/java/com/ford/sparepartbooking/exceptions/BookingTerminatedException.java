package com.ford.sparepartbooking.exceptions;

public class BookingTerminatedException extends RuntimeException {
    public BookingTerminatedException() {
        super("Booking with the given request cannot be processed");
    }
}
