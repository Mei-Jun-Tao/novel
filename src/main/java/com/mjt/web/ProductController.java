package com.mjt.web;

import com.mjt.pojo.Product;
import com.mjt.service.ProductImageService;
import com.mjt.service.ProductService;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductImageService productImageService;

    @PostMapping(value = "/products")
    public Object add(@RequestBody Product bena){
        Product product = bena;
        product.setPublishDate(new Date());
        productService.add(product);

        return product;
    }

    @DeleteMapping(value = "/products/{id}")
    public Object delete(@PathVariable("id") int id){
        productService.delete(id);

        return null;
    }

    @PutMapping(value = "/products")
    public Object update(@RequestBody Product bean){
        productService.update(bean);

        return bean;
    }

    @GetMapping(value = "/products/{id}")
    public Product get(@PathVariable("id") int id){
        return productService.get(id);
    }

    @GetMapping(value = "/categories/{cid}/products")
    public Page4Navigator<Product> list(@PathVariable("cid") int cid, @RequestParam(value = "stary", defaultValue = "0") int stary, @RequestParam(value = "size", defaultValue = "5") int size){
        stary = stary<0?0:stary;

        Page4Navigator<Product> page = productService.list(cid, stary, size, 5);

        productImageService.getImage(page.getContent());

        //给书籍设置图片
        for(Product p : page.getContent()){
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getBrief());
            System.out.println(p.getFileImage().getId());
        }

        return page;
    }
}
