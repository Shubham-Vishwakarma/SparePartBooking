package com.ford.sparepartbooking.models.sparepart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SparePartRequest {
    private int userId;
    private String sparePart;
    private int quantity;
}
