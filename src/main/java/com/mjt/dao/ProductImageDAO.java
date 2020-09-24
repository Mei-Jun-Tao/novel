package com.mjt.dao;

import com.mjt.pojo.Product;
import com.mjt.pojo.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductImageDAO extends JpaRepository<ProductImage, Integer> {

    List<ProductImage> findByProductAndType(Product product, String type);
    List<ProductImage> findByType(String type);
}
