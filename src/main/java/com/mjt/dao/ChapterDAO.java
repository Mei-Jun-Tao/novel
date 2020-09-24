package com.mjt.dao;

import com.mjt.pojo.Chapter;
import com.mjt.pojo.Chapters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChapterDAO extends JpaRepository<Chapter, Integer> {

    Page<Chapter> findByChapters(Chapters chapters, Pageable pageable);
    List<Chapter> findByChapters(Chapters chapters);
}
