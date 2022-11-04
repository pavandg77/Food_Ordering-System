package com.example.foodorderingsystem.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Food {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="discount")
    private double discount;

    @Column(name="image_path")
    private String imagePath;

    @Column(name="category_id")
    private int categoryId;
}
