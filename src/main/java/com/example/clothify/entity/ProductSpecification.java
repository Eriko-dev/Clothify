package com.example.clothify.entity;


import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // Lọc theo category name
    public static Specification<Product> hasCategory(String categoryName) {
        return (root, query, criteriaBuilder) -> {
            if (categoryName == null || categoryName.isEmpty()) {
                return criteriaBuilder.conjunction(); // không filter nếu null
            }
            // So sánh tên category (Category.name) với categoryName
            return criteriaBuilder.equal(root.get("category").get("name"), categoryName);
        };
    }

    // Lọc theo brand name
    public static Specification<Product> hasBrand(String brandName) {
        return (root, query, criteriaBuilder) -> {
            if (brandName == null || brandName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("brand").get("name"), brandName);
        };
    }

    // Lọc theo khoảng giá
    public static Specification<Product> hasPriceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null && maxPrice == null) {
                return criteriaBuilder.conjunction();
            } else if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
            } else if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
            } else {
                return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
            }
        };
    }

    // Lọc theo tên sản phẩm (like)
    public static Specification<Product> hasNameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }
}