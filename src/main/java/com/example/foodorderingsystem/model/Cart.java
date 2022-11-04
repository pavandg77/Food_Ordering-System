package com.example.foodorderingsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="food_id")
    private int foodId;

    @Column(name="user_id")
    private int userId;

    @Column(name="date")
    private String date;

    @Column(name="quantity")
    private int quantity;

}
