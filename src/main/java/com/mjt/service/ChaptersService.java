package com.mjt.service;

import com.mjt.dao.ChaptersDAO;
import com.mjt.pojo.Chapters;
import com.mjt.pojo.Product;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChaptersService {

    @Autowired
    ChaptersDAO chaptersDAO;

    @Autowired
    ProductService productService;

    public void add(Chapters chapters){
        chaptersDAO.save(chapters);
    }

    public void delete(int id){
        chaptersDAO.delete(id);
    }

    public void update(Chapters chapters){
        chaptersDAO.save(chapters);
    }
    public Chapters get(int id){
        return chaptersDAO.getOne(id);
    }

    public List<Chapters> list(int pid){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        Product product = productService.get(pid);

        return chaptersDAO.findByProduct(product);
    }

    public Page4Navigator<Chapters> list(int start, int size, int navigatePages, int pid){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Product product = productService.get(pid);
        Pageable pageable = new PageRequest(start, size, sort);
        Page page = chaptersDAO.findByProduct(product, pageable);
        return new Page4Navigator<Chapters>(page, navigatePages);
    }
}
