package com.ford.sparepartbooking.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
    private int userId;
    private Iterable<BookingItem> spareParts;
}
