package com.example.foodorderingsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="order_id")
    private String orderId;

    @Column(name="user_id")
    private int userId;

    @Column(name="food_id")
    private int foodId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="order_date")
    private String orderDate;

    @Column(name="delivery_date")
    private String deliveryDate;

    @Column(name="delivery_status")
    private String deliveryStatus;
}
