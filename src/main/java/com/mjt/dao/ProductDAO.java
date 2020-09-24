package com.mjt.dao;

import com.mjt.pojo.Category;
import com.mjt.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Integer> {

    Page<Product> findByCategory(Category category, Pageable pageable);
    List<Product> findByCategory(Category category);
}
