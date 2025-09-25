package com.example.clothify.controller;

import com.example.clothify.entity.Product;
import com.example.clothify.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable int id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PutMapping("/{id}/status")
    public Product updateStatus(@PathVariable int id, @RequestParam boolean status) {
        return productService.updateStatus(id, status);
    }
}
