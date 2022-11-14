package com.ford.sparepartbooking.exceptions;

public class SparePartAbsentException extends RuntimeException {
    public SparePartAbsentException(long sparePartId) {
        super("No Spare Part found with id " + sparePartId);
    }
}
