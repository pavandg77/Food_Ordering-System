package com.example.foodorderingsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.foodorderingsystem.model.Category;
import com.example.foodorderingsystem.model.Food;
import com.example.foodorderingsystem.repository.CategoryRepo;
import com.example.foodorderingsystem.repository.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CategoryController {

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @PostMapping("/addcategory")
    public ModelAndView addcategory(@ModelAttribute Category category) {
        ModelAndView  mv = new ModelAndView();

        categoryRepo.save(category);

        mv.addObject("status", "Category Added Successfully!");
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/deletecategory")
    public ModelAndView deleteCategory(@RequestParam("categoryId") int categoryId) {
        ModelAndView  mv = new ModelAndView();

        Category category = null;
        Optional<Category> o = categoryRepo.findById(categoryId);

        if(o.isPresent()) {
            category = o.get();
        }

        categoryRepo.delete(category);

        List<Food> foods = foodRepo.findByCategoryId(categoryId);

        for(Food food : foods) {
            foodRepo.delete(food);
        }

        mv.addObject("status", "Category Deleted Successfully!");
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/category")
    public ModelAndView category(@RequestParam("categoryId") int  categoryId) {
        ModelAndView mv = new ModelAndView();
        List<Food> foods = new ArrayList<>();
        if(categoryId == 0) {
            foods = foodRepo.findAll();
        }

        else {
            foods = foodRepo.findByCategoryId(categoryId);
        }

        mv.addObject("foods", foods);
        mv.addObject("sentFromOtherSource", "yes");
        mv.setViewName("index");

        return mv;
    }
}


