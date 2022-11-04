package com.example.foodorderingsystem.repository;

import com.example.foodorderingsystem.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {

    List<Cart> findByUserId(int userId);
    Long countByUserId(int userId);
}
