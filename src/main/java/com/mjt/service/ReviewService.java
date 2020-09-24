package com.mjt.service;

import com.mjt.dao.ReviewDAO;
import com.mjt.pojo.Product;
import com.mjt.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    
    @Autowired
    ReviewDAO reviewDAO;

    @Autowired
    ProductService productService;

    public void add(Review review){
        reviewDAO.save(review);
    }

    public void delete(int id){
        reviewDAO.delete(id);
    }

    public void update(Review review){
        reviewDAO.save(review);
    }
    public Review get(int id){
        return reviewDAO.getOne(id);
    }

    public List<Review> list(int pid){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Product product = productService.get(pid);

        return reviewDAO.findByProduct(product);
    }
}
