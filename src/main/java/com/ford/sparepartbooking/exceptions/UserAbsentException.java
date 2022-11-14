package com.ford.sparepartbooking.exceptions;

public class UserAbsentException extends RuntimeException {
    public UserAbsentException(long userId) {
        super("No user with userId " + userId);
    }
}
