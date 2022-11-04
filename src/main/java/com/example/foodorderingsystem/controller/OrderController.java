package com.example.foodorderingsystem.controller;


import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.foodorderingsystem.model.Cart;
import com.example.foodorderingsystem.model.Order;
import com.example.foodorderingsystem.model.User;
import com.example.foodorderingsystem.repository.CartRepo;
import com.example.foodorderingsystem.repository.OrderRepo;
import com.example.foodorderingsystem.utility.Constants;
import com.example.foodorderingsystem.utility.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderRepo orderRepo;

    @PostMapping("/order")
    public ModelAndView orderfoods(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        User user = (User)session.getAttribute("active-user");

        String orderId = Helper.getAlphaNumericOrderId(10);
        String orderedDate = LocalDate.now().toString();

        List<Cart> carts = cartRepo.findByUserId(user.getId());

        for(Cart cart : carts) {
            Order order = new Order();
            order.setOrderDate(orderedDate);
            order.setOrderId(orderId);
            order.setUserId(user.getId());
            order.setQuantity(cart.getQuantity());
            order.setFoodId(cart.getFoodId());
            order.setDeliveryStatus(Constants.DeliveryStatus.PENDING.value());
            order.setDeliveryDate(Constants.DeliveryStatus.PENDING.value());
            orderRepo.save(order);
            cartRepo.delete(cart);
        }


        mv.addObject("status","Order placed Successfully, Order Id is "+orderId);
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/myorder")
    public ModelAndView goToMyOrder(HttpSession session) {
        User user = (User)session.getAttribute("active-user");
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderRepo.findByUserId(user.getId());
        mv.addObject("orders", orders);
        mv.setViewName("myorder");
        return mv;
    }

    @GetMapping("/searchorderbyid")
    public ModelAndView searchByOrderId(@RequestParam("orderid") String orderId) {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderRepo.findByOrderId(orderId);
        mv.addObject("orders", orders);
        mv.setViewName("myorder");
        return mv;
    }

    @GetMapping("/searchorderbydate")
    public ModelAndView searchByOrderDate(@RequestParam("orderdate") String orderDate, HttpSession session) {
        User user = (User)session.getAttribute("active-user");
        ModelAndView mv = new ModelAndView();
        List<Order> orders = orderRepo.findByOrderDateAndUserId(orderDate, user.getId());
        mv.addObject("orders", orders);
        mv.setViewName("myorder");
        return mv;
    }

    @PostMapping("/checkout")
    public ModelAndView searchByOrderDate(@RequestParam("amount") String amount) {

        ModelAndView mv = new ModelAndView();

        mv.addObject("amount", amount);
        mv.setViewName("checkout");
        return mv;
    }

    @GetMapping("/updatedeliverydate")
    public ModelAndView addDeliveryStatus(@RequestParam("orderId") String orderId, @RequestParam("deliveryStatus") String deliveryStatus, @RequestParam("deliveryDate") String deliveryDate ) {
        ModelAndView mv = new ModelAndView();

        List<Order> orders = this.orderRepo.findByOrderId(orderId);

        for(Order order : orders) {
            order.setDeliveryDate(deliveryDate);
            order.setDeliveryStatus(deliveryStatus);
            this.orderRepo.save(order);
        }
        mv.addObject("status", "Order Delivery Status Updated.");
        mv.setViewName("index");
        return mv;
    }
}
