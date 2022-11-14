package com.ford.sparepartbooking.models.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ford.sparepartbooking.models.sparepart.SparePart;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sparePartId", nullable = false)
    private SparePart sparePart;

    @Column(name = "quantity", nullable = false)
    private int quantity;
}
