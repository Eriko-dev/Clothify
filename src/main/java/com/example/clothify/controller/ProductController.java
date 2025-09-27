package com.example.clothify.controller;

import com.example.clothify.entity.Product;
import com.example.clothify.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "User API", description = "Quản lý sản phẩm")

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Danh sách tất cả sản phẩm", description = "Trả về toàn bộ sản phẩm trong hệ thống")

    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/search")
    public Page<Product> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        return productService.searchProducts(name, category, brand, minPrice, maxPrice, page, size, sortBy, sortDir);
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
