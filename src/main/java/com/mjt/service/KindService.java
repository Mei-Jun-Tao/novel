package com.mjt.service;

import com.mjt.dao.KindDAO;
import com.mjt.pojo.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KindService {

    @Autowired
    KindDAO kindDAO;

    public List<Kind> list(){
        //使用Sort对象来做id排序
        Sort sort = new Sort(Sort.Direction.ASC, "id");

        return kindDAO.findAll(sort);
    }

    public Kind get(int id){
        return kindDAO.getOne(id);
    }
}
