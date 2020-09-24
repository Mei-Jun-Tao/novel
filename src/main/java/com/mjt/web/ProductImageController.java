package com.mjt.web;

import com.mjt.pojo.Product;
import com.mjt.pojo.ProductImage;
import com.mjt.service.ProductImageService;
import com.mjt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.File;
import java.util.List;

@RestController
public class ProductImageController {

    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductService productService;

    @PostMapping(value = "/productImages/{pid}")
    public Object add(@PathVariable("pid") int pid, ProductImage bena, MultipartFile image, HttpServletRequest request){
        ProductImage pi = bena;
        Product product = productService.get(pid);
        bena.setProduct(product);
        productImageService.add(bena);

        try {
            //创建文件对象
            File fileFolder = new File(request.getServletContext().getRealPath("img"), "/" + bena.getType());
            File file = new File(fileFolder, bena.getId() + ".jpg");

            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();

            image.transferTo(file);

        }catch(Exception e){
            e.printStackTrace();
            return "上传失败!" + e.getMessage();
        }
        return bena;
    }

    @DeleteMapping(value = "/productImages/{id}")
    public Object delete(@PathVariable("id") int id, HttpServletRequest request){
        ProductImage bena = productImageService.get(id);
        //获取那个文件对象
        File fileFolder = new File(request.getServletContext().getRealPath("img"), "/" + bena.getType());
        File file = new File(fileFolder, bena.getId() + ".jpg");
        file.delete();

        productImageService.delete(id);

        return null;
    }

    @GetMapping(value = "/products/{pid}/productImages")
    public List<ProductImage> list(@PathVariable("pid") int pid, @RequestParam("type") String type){
        return productImageService.list(pid, type);
    }
}
