package com.ford.sparepartbooking.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class Error {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public Error(HttpStatus status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public Error(HttpStatus status, String message, String errors) {
        this.status = status;
        this.message = message;
        this.errors = List.of(errors);
    }
}
