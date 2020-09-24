package com.mjt.web;


import com.mjt.pojo.Kind;
import com.mjt.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KindController {
    //前后端分离数据是通过RESTFUL接口来取的，所以RESTFUL服务放在这里

    @Autowired
    KindService kindService;

    @GetMapping("/kinds")
    public List<Kind> list(){
        return kindService.list();
    }

    @GetMapping("/kinds/{id}")
    public Object get(@PathVariable("id") int id){
        return kindService.get(id);
    }
}
