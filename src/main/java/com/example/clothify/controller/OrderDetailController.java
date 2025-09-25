package com.example.clothify.controller;

import com.example.clothify.entity.Order;
import com.example.clothify.entity.OrderDetail;
import com.example.clothify.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/order/{orderId}")
    public List<OrderDetail> getByOrder(@PathVariable int orderId) {
        return orderDetailService.getByOrder(orderId);
    }


    @PostMapping
    public OrderDetail create(@RequestBody OrderDetail orderDetail) {
        return orderDetailService.create(orderDetail);
    }
}
