package com.mjt.dao;

import com.mjt.pojo.Product;
import com.mjt.pojo.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review, Integer> {

    List<Review> findByProduct(Product product);
}
