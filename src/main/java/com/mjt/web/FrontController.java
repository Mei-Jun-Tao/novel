package com.mjt.web;

import com.mjt.pojo.Category;
import com.mjt.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FrontController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/homes")
    public Object home(){

        List<Category> cs = categoryService.list(0, 10, 8).getContent();

        //设置产品
        categoryService.listProduct(cs);

        List<Category> categories = new ArrayList<>();
        for(Category c : cs){
            if(!c.getProducts().isEmpty()){
                categories.add(c);
            }
        }
        for(Category c : categories){
            System.out.println("ha1" + c.getId());
            System.out.println("ha2" + c.getName());
            System.out.println("ha3" + c.getProducts().toArray());
        }

        return categories;
    }
}
