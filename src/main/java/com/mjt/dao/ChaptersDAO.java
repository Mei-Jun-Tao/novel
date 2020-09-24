package com.mjt.dao;

import com.mjt.pojo.Chapters;
import com.mjt.pojo.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChaptersDAO extends JpaRepository<Chapters, Integer> {

    Page<Chapters> findByProduct(Product product, Pageable pageable);
    List<Chapters> findByProduct(Product product);
}
