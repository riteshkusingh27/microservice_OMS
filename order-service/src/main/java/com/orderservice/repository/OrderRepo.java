package com.orderservice.repository;

import com.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends
        JpaRepository<Order, Long> {
}
