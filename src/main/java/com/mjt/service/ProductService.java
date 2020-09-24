package com.mjt.service;

import com.mjt.dao.ProductDAO;
import com.mjt.pojo.Category;
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
public class ProductService {
    
    @Autowired
    ProductDAO productDAO;

    @Autowired
    CategoryService categoryService;

    public void add(Product product){
        productDAO.save(product);
    }

    public void delete(int id){
        productDAO.delete(id);
    }

    public void update(Product product){
        productDAO.save(product);
    }
    public Product get(int id){
        return productDAO.getOne(id);
    }

    public Page4Navigator<Product> list(int cid, int strat, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Category category = categoryService.get(cid);
        Pageable pageable = new PageRequest(strat, size, sort);
        Page page = productDAO.findByCategory(category, pageable);

        return new Page4Navigator<Product>(page, navigatePages);
    }

    public List<Product> list(int cid){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Category category = categoryService.get(cid);

        return productDAO.findByCategory(category);
    }
}
