package com.example.foodorderingsystem.repository;

import com.example.foodorderingsystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {

    List<Order> findByOrderId(String orderId);
    List<Order> findByUserId(int userId);
    List<Order> findByOrderDate(String orderDate);
    List<Order> findByOrderDateAndUserId(String orderDate, int userId);
}
