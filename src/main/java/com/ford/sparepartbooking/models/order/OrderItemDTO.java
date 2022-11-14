package com.ford.sparepartbooking.models.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private long orderItemId;
    private String sparePart;
    private int quantity;
}
