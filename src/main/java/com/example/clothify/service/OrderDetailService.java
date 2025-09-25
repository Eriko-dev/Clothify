package com.example.clothify.service;

import com.example.clothify.entity.Order;
import com.example.clothify.entity.OrderDetail;
import com.example.clothify.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<OrderDetail> getByOrder(Order order) {
        return orderDetailRepository.findByOrder(order);
    }
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }
}
