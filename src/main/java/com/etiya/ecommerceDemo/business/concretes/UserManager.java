package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserDao userDao;

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(Long id) {
        return userDao.findById(id).orElseThrow();
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
}
