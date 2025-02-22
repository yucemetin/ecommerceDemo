package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.user.UpdateUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UpdateUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {

    private UserService userService;

    @GetMapping
    public DataResult<List<ListUserResponse>> getAll() {
        return userService.getAll();
    }

    @GetMapping("/page")
    public DataResult<Slice<ListUserResponse>> getAll(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return userService.getAllWithPage(pageable);
    }

    @GetMapping("/{id}")
    public DataResult<UserDetailResponse> getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @PostMapping
    public DataResult<AddUserResponse> addProduct(@RequestBody @Valid AddUserRequest addUserRequest) {
        return userService.addUser(addUserRequest);
    }

    @PutMapping("")
    public DataResult<UpdateUserResponse> updateUser(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
