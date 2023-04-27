package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import jakarta.validation.Valid;
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
    public UserDetailResponse getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public AddUserResponse addProduct(@RequestBody @Valid AddUserRequest addUserRequest) throws Exception {
        return userService.addUser(addUserRequest);
    }
}
