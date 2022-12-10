package com.example.shopping_mall.repository;

import com.example.shopping_mall.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
