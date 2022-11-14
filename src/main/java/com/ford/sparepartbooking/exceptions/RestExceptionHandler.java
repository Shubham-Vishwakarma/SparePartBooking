package com.ford.sparepartbooking.exceptions;

import com.ford.sparepartbooking.models.Error;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(UserAbsentException.class)
    protected ResponseEntity<Object> handleUserAbsent(UserAbsentException ex) {
        Error error = new Error(HttpStatus.NOT_FOUND, "Not found", ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(SparePartAbsentException.class)
    protected ResponseEntity<Object> handleSparePartAbsent(SparePartAbsentException ex) {
        Error error = new Error(HttpStatus.NOT_FOUND, "Not found", ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    protected ResponseEntity<Object> handlePermissionDenied(PermissionDeniedException ex) {
        Error error = new Error(HttpStatus.FORBIDDEN, "Access Denied", ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(OutOfStockException.class)
    protected ResponseEntity<Object> handleOutofStock(OutOfStockException ex) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Out of Stock", ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(BookingTerminatedException.class)
    protected ResponseEntity<Object> handleBookingTerminated(BookingTerminatedException ex) {
        Error error = new Error(HttpStatus.BAD_REQUEST, "Booking Terminated", ex.getMessage());
        return new ResponseEntity<>(error, error.getStatus());
    }

}
