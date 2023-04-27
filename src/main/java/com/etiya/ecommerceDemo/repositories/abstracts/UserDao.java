package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.business.dtos.responses.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.UserDetailResponse;
import com.etiya.ecommerceDemo.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users", nativeQuery = true)
    List<User> findAllUser();

    User findByEmail(String email);

    @Query(value = "select new" +
            " com.etiya.ecommerceDemo.business.dtos.responses.UserDetailResponse(u.id,u.first_name,u.last_name,u.email,u.password)" +
            " from User u where u.id = :id", nativeQuery = false)
    UserDetailResponse getUserById(Long id);

    @Query(value = "select new" +
            " com.etiya.ecommerceDemo.business.dtos.responses.ListUserResponse(u.id,u.first_name,u.last_name,u.email,u.password)" +
            " from User u", nativeQuery = false)
    List<ListUserResponse> getAll();
}
