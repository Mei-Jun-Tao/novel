package com.mjt.web;

import com.mjt.pojo.ProductImage;
import com.mjt.pojo.User;
import com.mjt.pojo.Users;
import com.mjt.service.ProductImageService;
import com.mjt.service.UserService;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProductImageService productImageService;

    //注册处理
    @PostMapping(value = "/users")
    public Object add(@RequestBody User bena, HttpSession session){
        //为了防止用户起html样的名字所以要转义一下
        bena.setName(HtmlUtils.htmlEscape(bena.getName()));
        //判断用户是否存在
        if(userService.isUser(bena))
            return "用户已存在!";
        userService.add(bena);
        session.setAttribute("user", bena);

        return null;
    }

    //退出登录
    @DeleteMapping(value = "/users")
    public Object delete(HttpSession session){
        session.removeAttribute("user");
        return null;
    }

    //更新用户信息
    @PutMapping(value = "/users")
    public Object update(@RequestBody User bena, HttpSession session){
        bena.setName(HtmlUtils.htmlEscape(bena.getName()));
        userService.update(bena);
        session.setAttribute("user", bena);
        return bena;
    }

    //获取用户信息
    @GetMapping(value = "/users")
    public Object get(HttpSession session) {
        User user = (User) session.getAttribute("user");
        productImageService.getFileImage(user);
        return session.getAttribute("user");
    }

    //登录处理
    @PostMapping(value = "/logins")
    public Object login(@RequestBody Users bena, HttpSession session){

        String name = HtmlUtils.htmlEscape(bena.getName());
        String password = bena.getPassword();

        User user = new User();
        user.setName(name);
        user.setCellNumber(name);
        user.setEmallAddress(name);
        user.setPassword(password);
        //判断账号和密码是否正确
        User u = userService.isLogin(user);
        if(u == null)
            return "账号或密码错误";

        session.setAttribute("user", u);
        return null;
    }

    //上传头像
    @PostMapping(value = "/updateImages")
    public Object setImage(ProductImage bena, MultipartFile image, HttpServletRequest request){
        System.out.println(bena.getType());
        //判断是否有图片，有就删除
        List<ProductImage> pis = productImageService.list(bena.getType());
        if(!pis.isEmpty()){
            ProductImage pi = pis.get(0);

            File fileFold = new File(request.getServletContext().getRealPath("img"), "/" + pi.getType());
            File file = new File(fileFold, pi.getId() + ".jpg");
            file.delete();
            productImageService.delete(pi.getId());
        }
        productImageService.add(bena);

        try {
            //创建目录
            File fileFold = new File(request.getServletContext().getRealPath("img"), "/" + bena.getType());
            File file = new File(fileFold, bena.getId() + ".jpg");
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            image.transferTo(file);
        }catch(Exception e){
            e.printStackTrace();
            return "上传失败" + e.getMessage();
        }
        return bena;
    }
}
