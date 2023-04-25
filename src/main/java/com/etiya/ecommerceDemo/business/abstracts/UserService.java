package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(Long id);

    void addUser(User user) throws Exception;
}
