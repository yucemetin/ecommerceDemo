package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.dtos.requests.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.ListUserResponse;
import com.etiya.ecommerceDemo.entities.concretes.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {

    private UserService userService;

    @GetMapping
    public List<ListUserResponse> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ListUserResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public void addProduct(@RequestBody AddUserRequest addUserRequest) throws Exception {
        userService.addUser(addUserRequest);
    }
}
