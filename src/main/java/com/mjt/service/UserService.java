package com.mjt.service;

import com.mjt.dao.UserDAO;
import com.mjt.pojo.ProductImage;
import com.mjt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public void add(User user){
        userDAO.save(user);
    }

    public void delete(int id){
        userDAO.delete(id);
    }

    public void update(User user){
        userDAO.save(user);
    }
    public User get(int id){
        return userDAO.getOne(id);
    }

    //判断用户是否存在
    public boolean isUser(User user){
        if(userDAO.findByName(user.getName()).isEmpty())
            return false;
        return true;
    }

    //判断账号和密码、手机号和密码、邮箱和密码是否正确
    public User isLogin(User user){
        List<User> name = userDAO.findByNameAndPassword(user.getName(), user.getPassword());
        List<User> cellNumber = userDAO.findByCellNumberAndPassword(user.getCellNumber(), user.getPassword());
        List<User> emallAddress = userDAO.findByEmallAddressAndPassword(user.getEmallAddress(), user.getPassword());

        if(!name.isEmpty())
            return name.get(0);
        if(!cellNumber.isEmpty())
            return cellNumber.get(0);
        if(!emallAddress.isEmpty())
            return emallAddress.get(0);

        return null;
    }

}
