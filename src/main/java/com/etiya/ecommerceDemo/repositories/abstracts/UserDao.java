package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Long> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUser();

    User findByEmail(String email);
}
