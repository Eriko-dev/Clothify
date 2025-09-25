package com.example.clothify.service;

import com.example.clothify.entity.Order;
import com.example.clothify.entity.User;
import com.example.clothify.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUser(int userId) {
        return orderRepository.findByUserId(userId);
    }
    public Order create(Order order){
        return orderRepository.save(order);
    }
    public Order updateStatus(int id, boolean status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
