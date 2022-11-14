package com.ford.sparepartbooking.models.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private long orderId;
    private Iterable<OrderItemDTO> orderItems;
}
