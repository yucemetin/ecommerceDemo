package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.UserDetailResponse;

import java.util.List;

public interface UserService {
    List<ListUserResponse> getAll();

    UserDetailResponse getById(Long id);

    AddUserResponse addUser(AddUserRequest addUserRequest) throws Exception;
}
