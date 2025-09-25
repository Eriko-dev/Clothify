package com.example.clothify.repository;

import com.example.clothify.entity.Order;
import com.example.clothify.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
    List<Order> findByUserId(int userId);
}
