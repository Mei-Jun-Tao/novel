package com.mjt.web;

import com.mjt.pojo.Category;
import com.mjt.service.CategoryService;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/categories")
    public Object add(@RequestBody Category bean){
        categoryService.add(bean);
        return bean;
    }

    @DeleteMapping(value = "/categories/{id}")
    public Object delete(@PathVariable("id") int id, HttpServletRequest request){
        categoryService.delete(id);
        return null;
    }

    @PutMapping(value = "/categories")
    public Object update(@RequestBody Category bena){
        categoryService.update(bena);
        return bena;
    }

    @GetMapping(value = "/categories/{id}")
    public Object get(@PathVariable("id") int id){
        return categoryService.get(id);
    }

    @GetMapping(value = "/kinds/{kid}/categories")
    public Page4Navigator<Category> list(@PathVariable("kid") int kid, @RequestParam(value = "stary", defaultValue = "0") int stary, @RequestParam(value = "size", defaultValue = "5") int size){
        //判断stary是否负数
        stary = stary<0 ?0:stary;
        //分页查询
        Page4Navigator pn = categoryService.list(stary, size, 8, kid);
        return pn;
    }
}
