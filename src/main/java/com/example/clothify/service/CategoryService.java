package com.example.clothify.service;

import com.example.clothify.entity.Category;
import com.example.clothify.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
    public Category create(Category category) {
        return categoryRepository.save(category);
    }
    public Category update(int id, Category category) {
        Category existing = categoryRepository.findById(id).orElseThrow();
        existing.setName(category.getName());
        return categoryRepository.save(existing);
    }
    public Category updateStatus(int id, String status) {
        Category category  = categoryRepository.findById(id).orElseThrow();
        category .setStatus(status);
        return categoryRepository.save(category );
    }

}
