package com.example.clothify.controller;

import com.example.clothify.entity.Category;
import com.example.clothify.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable int id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @PutMapping("/{id}/status")
    public Category updateStatus(@PathVariable int id, @RequestParam boolean status) {
        return categoryService.updateStatus(id, String.valueOf(status));
    }
}
