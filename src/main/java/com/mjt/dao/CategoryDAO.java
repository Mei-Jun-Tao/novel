package com.mjt.dao;

import com.mjt.pojo.Category;
import com.mjt.pojo.Kind;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category,Integer> {

    Page<Category> findByKind(Kind kind, Pageable pageable);
    Page<Category> findAllBy(Pageable pageable);
}
