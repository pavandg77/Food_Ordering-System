package com.example.foodorderingsystem.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.example.foodorderingsystem.model.Food;
import com.example.foodorderingsystem.repository.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@MultipartConfig
public class FoodController {
    @Autowired
    private FoodRepo foodRepo;

    @PostMapping("/addfood")
    public ModelAndView addProduct(HttpServletRequest request, HttpSession session) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();

        String name=request.getParameter("name");
        String description=request.getParameter("description");
        Double price=Double.parseDouble(request.getParameter("price"));
        Double discount=Double.parseDouble(request.getParameter("discount"));
        int categoryId=Integer.parseInt(request.getParameter("categoryId"));
        Part part=request.getPart("image");

        String fileName=part.getSubmittedFileName();

        String uploadPath="/home/pavan/Food-Ordering-System/src/main/webapp/resources/productpic"+fileName;

        try
        {
            FileOutputStream fos=new FileOutputStream(uploadPath);
            InputStream is=part.getInputStream();

            byte[] data=new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        Food food = new Food();
        food.setCategoryId(categoryId);
        food.setImagePath(fileName);
        food.setName(name);
        food.setPrice(price);
        food.setDiscount(discount);
        food.setDescription(description);

        food = foodRepo.save(food);

        if(food != null )
        {
            mv.addObject("status", "Food Added Successfully.");
        }

        else
        {
            mv.addObject("status", "Failed to add food.");
        }
        mv.setViewName("index");

        return mv;
    }

    @PostMapping("/updatefood")
    public ModelAndView updateProduct(HttpServletRequest request, HttpSession session) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String description=request.getParameter("description");
        Double price=Double.parseDouble(request.getParameter("price"));
        Double discount=Double.parseDouble(request.getParameter("discount"));
        int categoryId=Integer.parseInt(request.getParameter("categoryId"));
        Part part=request.getPart("image");

        String fileName=part.getSubmittedFileName();

        String uploadPath="/home/pavan/Food-Ordering-System/src/main/webapp/resources/productpic"+fileName;

        try
        {
            FileOutputStream fos=new FileOutputStream(uploadPath);
            InputStream is=part.getInputStream();

            byte[] data=new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        Food food = new Food();
        food.setCategoryId(categoryId);
        food.setImagePath(fileName);
        food.setName(name);
        food.setPrice(price);
        food.setDiscount(discount);
        food.setDescription(description);
        food.setId(id);
        food = foodRepo.save(food);

        if(food != null )
        {
            mv.addObject("status", "Food updated Successfully.");
        }

        else
        {
            mv.addObject("status", "Failed to update food.");
        }

        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/searchfood")
    public ModelAndView searchProductByName(@RequestParam("foodname") String foodName) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();
        List<Food> foods = new ArrayList<>();
        foods = foodRepo.findByNameContainingIgnoreCase(foodName);

        mv.addObject("sentFromOtherSource","yes");
        mv.addObject("foods", foods);
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/food")
    public ModelAndView getFood(@RequestParam("foodId") int foodId) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();
        Food food = null;
        Optional<Food> o = foodRepo.findById(foodId);

        if(o.isPresent()) {
            food = o.get();
        }

        mv.addObject("food", food);
        mv.setViewName("food");

        return mv;
    }

    @GetMapping("/deletefood")
    public ModelAndView deleteFood(@RequestParam("foodId") int foodId) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();

        Food food = null;
        Optional<Food> o = foodRepo.findById(foodId);

        if(o.isPresent()) {
            food = o.get();
        }

        foodRepo.delete(food);

        mv.addObject("status", "Food Deleted Successfully!");
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/updatefood")
    public ModelAndView updateFood(@RequestParam("foodId") int foodId) throws IOException, ServletException {
        ModelAndView mv = new ModelAndView();

        Food food = null;
        Optional<Food> o = foodRepo.findById(foodId);

        if(o.isPresent()) {
            food = o.get();
        }

        mv.addObject("food", food);
        mv.setViewName("updatefood");

        return mv;
    }

}
