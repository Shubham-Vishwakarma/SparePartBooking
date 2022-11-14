package com.ford.sparepartbooking.exceptions;

public class OutOfStockException extends RuntimeException{
    public OutOfStockException(String sparePartName) {
        super("Spare Part " + sparePartName + " is out of stock");
    }
}
