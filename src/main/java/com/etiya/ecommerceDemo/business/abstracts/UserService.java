package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;

import java.util.List;

public interface UserService {
    List<ListUserResponse> getAll();

    UserDetailResponse getById(Long id);

    AddUserResponse addUser(AddUserRequest addUserRequest) throws Exception;
}
