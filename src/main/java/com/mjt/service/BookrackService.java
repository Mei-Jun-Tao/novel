package com.mjt.service;

import com.mjt.dao.BookrackDAO;
import com.mjt.pojo.Bookrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookrackService {

    @Autowired
    BookrackDAO bookrackDAO;

    public void add(Bookrack bookrack){
        bookrackDAO.save(bookrack);
    }

    public Bookrack get(int id){
        return bookrackDAO.getOne(id);
    }
}
