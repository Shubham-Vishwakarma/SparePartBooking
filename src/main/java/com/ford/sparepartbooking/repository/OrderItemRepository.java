package com.ford.sparepartbooking.repository;

import com.ford.sparepartbooking.models.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}