package com.example.clothify.service;

import com.example.clothify.entity.Product;
import com.example.clothify.entity.ProductSpecification;
import com.example.clothify.repository.CategoryRepository;
import com.example.clothify.repository.ProductRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }
    public Product create(Product product) {
        return productRepository.save(product);
    }
    public Product update(int id, Product product) {
        Product existing  = productRepository.findById(id).orElseThrow();
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        existing.setCategory(product.getCategory());
        return productRepository.save(existing);
    }
    public Product updateStatus(int id, boolean status) {
        Product product  = productRepository.findById(id).orElseThrow();
        product.setStatus(status);
        return productRepository.save(product);
    }

    public Page<Product> searchProducts(
            String name, String category, String brand,
            Double minPrice, Double maxPrice,
            int page, int size, String sortBy, String sortDir
    ) {
        Sort sort = Sort.by(sortBy);
        sort = sortDir.equalsIgnoreCase("desc") ? sort.descending() : sort.ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Product> spec = ProductSpecification.hasNameLike(name)
                .and(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.hasBrand(brand))
                .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice));

        return productRepository.findAll(spec, pageable);
    }
}

