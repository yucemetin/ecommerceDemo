package com.etiya.ecommerceDemo.dataAccess.contretes;

import com.etiya.ecommerceDemo.entities.concrete.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
}