package com.example.clothify.controller;

import com.example.clothify.entity.Order;
import com.example.clothify.entity.User;
import com.example.clothify.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(@RequestBody User user) {
        return orderService.getOrdersByUser(user);
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.create(order);
    }

    @PutMapping("/{id}/status")
    public Order updateStatus(@PathVariable int id, @RequestParam String status) {
        return orderService.updateStatus(id, Boolean.parseBoolean(status));
    }
}
