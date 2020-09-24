package com.mjt.service;

import com.mjt.dao.ProductImageDAO;
import com.mjt.pojo.Product;
import com.mjt.pojo.ProductImage;
import com.mjt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImageService {
    
    @Autowired
    ProductImageDAO productImageDAO;

    @Autowired
    ProductService productService;

    static final String BookImage = "Book_Image";
    static final String HeadImage = "Head_Image";

    public void add(ProductImage productImage){
        productImageDAO.save(productImage);
    }

    public void delete(int id){
        productImageDAO.delete(id);
    }

    public void update(ProductImage productImage){
        productImageDAO.save(productImage);
    }
    public ProductImage get(int id){
        return productImageDAO.getOne(id);
    }

    public List<ProductImage> list(int pid, String type){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Product product = productService.get(pid);

        return productImageDAO.findByProductAndType(product, type);
    }

    public List<ProductImage> list(String type){
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return productImageDAO.findByType(type);
    }

    //为书本设置图片
    public void getImage(List<Product> proucts){
        for(Product product : proucts){
            getImage(product);
        }
    }
    public void getImage(Product product){
        List<ProductImage> pi = list(product.getId(), "Book_Image");
        if(!pi.isEmpty())
            product.setFileImage(pi.get(0));
        else
            product.setFileImage(new ProductImage());
    }

    //给用户设置头像
    public void getFileImage(User user){
        List<ProductImage> pi = list("Head_Image");
        if(!pi.isEmpty())
            user.setFileImage(pi.get(0));
        else
            user.setFileImage(new ProductImage());
    }
}
