package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.ListUserResponse;

import java.util.List;

public interface UserService {
    List<ListUserResponse> getAll();

    ListUserResponse getById(Long id);

    AddUserResponse addUser(AddUserRequest addUserRequest) throws Exception;
}
