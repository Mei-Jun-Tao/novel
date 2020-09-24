package com.mjt.dao;

import com.mjt.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findByName(String name);
    List<User> findByNameAndPassword(String name, String password);
    List<User> findByCellNumberAndPassword(String name, String password);
    List<User> findByEmallAddressAndPassword(String name, String password);
}
