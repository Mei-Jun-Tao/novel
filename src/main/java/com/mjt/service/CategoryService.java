package com.mjt.service;

import com.mjt.dao.CategoryDAO;
import com.mjt.pojo.Category;
import com.mjt.pojo.Kind;
import com.mjt.pojo.Product;
import com.mjt.pojo.User;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    KindService kindService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    UserService userService;

    public void add(Category category){
        categoryDAO.save(category);
    }

    public void delete(int id){
        categoryDAO.delete(id);
    }

    public void update(Category category){
        categoryDAO.save(category);
    }
    public Category get(int id){
        return categoryDAO.getOne(id);
    }

    public Page4Navigator<Category> list(int start, int size, int navigatePages, int kid){
        Kind kind = kindService.get(kid);
        //id正排序
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        //进行分页
        Pageable pageable = new PageRequest(start, size, sort);
        //查询数据
        Page page = categoryDAO.findByKind(kind, pageable);

        //进行数据显示
        return new Page4Navigator<>(page, navigatePages);
    }

    public Page4Navigator<Category> list(int start, int size, int navigatePages){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page page = categoryDAO.findAllBy(pageable);

        return new Page4Navigator<>(page, navigatePages);
    }

    //为这些分类设置产品
    public void listProduct(List<Category> cs){
        for(Category c : cs){
            listProduct(c);
        }
    }
    public void listProduct(Category c){
        List<Product> ps = productService.list(c.getId(), 0, 3, 5).getContent();
        //给这些产品设置图片
        productImageService.getImage(ps);

        c.setProducts(ps);
    }
}
