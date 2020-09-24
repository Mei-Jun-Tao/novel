package com.mjt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {
    //为了做前后端分离专门用来做页面跳转的

    //前台页面跳转
    @GetMapping(value = "/front")
    public String front(){
        return "redirect:/home";
    }
    @GetMapping(value = "/home")
    public String home(){
        return "front/home";
    }


    //后台页面跳转
    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:/adminLogin";
    }

    @GetMapping(value = "/adminUpdate")
    public String listUpdate(){
        return "admin/adminUpdate";
    }

    @GetMapping(value = "/adminLogin")
    public String listLogin(){
        return "admin/adminLogin";
    }

    @GetMapping(value = "/adminRegister")
    public String listRegieter(){
        return "admin/adminRegister";
    }

    @GetMapping(value="/admin_kind_list")
    public String listKind(){
        return "admin/listKind";
    }

    @GetMapping(value="/admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }

    @GetMapping(value="/admin_category_edit")
    public String editCategory(){
        return "admin/editCategory";
    }

    @GetMapping(value = "/admin_product_list")
    public String listProduct(){
        return "admin/listProduct";
    }

    @GetMapping(value = "/admin_product_edit")
    public String editProduct(){
        return "admin/editProduct";
    }

    @GetMapping(value = "/admin_chapters_list")
    public String listChapters(){
        return "admin/listChapters";
    }

    @GetMapping(value = "/admin_chapters_edit")
    public String editChapters(){
        return "admin/editChapters";
    }

    @GetMapping(value = "/admin_chapter_list")
    public String listChapter(){
        return "admin/listChapter";
    }

    @GetMapping(value = "/admin_productImage_list")
    public String listProductImage(){
        return "admin/listProductImage";
    }
}
