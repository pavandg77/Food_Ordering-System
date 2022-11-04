package com.example.foodorderingsystem.repository;

import com.example.foodorderingsystem.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface FoodRepo extends JpaRepository<Food,Integer> {

    List<Food> findByNameContainingIgnoreCase(String name);
    List<Food> findByCategoryId(int categoryId);

    @Query(value="delete from Food f where f.categoryId = :categoryId")
    void deleteProductByCategoryId(@Param("categoryId") int categoryId);

}
