package com.ford.sparepartbooking.repository;

import com.ford.sparepartbooking.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}