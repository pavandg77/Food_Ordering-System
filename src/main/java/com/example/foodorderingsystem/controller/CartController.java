package com.example.foodorderingsystem.controller;

import java.util.List;
import java.util.Optional;

import com.example.foodorderingsystem.model.Cart;
import com.example.foodorderingsystem.repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class CartController {

    @Autowired
    private CartRepo cartRepo;

    @GetMapping("/addToCart")
    public ModelAndView addtoCart(@ModelAttribute Cart cart) {
        ModelAndView mv = new ModelAndView();

        cartRepo.save(cart);
        mv.addObject("status", "Foods added to cart!");
        mv.setViewName("index");

        return mv;
    }

    @GetMapping("/deletecart")
    public ModelAndView deleteProductFromCart(@RequestParam("cartId") int cartId) {
        ModelAndView mv = new ModelAndView();

        Cart cart = new Cart();

        Optional<Cart> o = cartRepo.findById(cartId);
        if (o.isPresent()) {
            cart = o.get();
        }

        cartRepo.delete(cart);

        mv.addObject("status", "Selected Food removed from Cart!");
        mv.setViewName("index");

        return mv;
    }
}
