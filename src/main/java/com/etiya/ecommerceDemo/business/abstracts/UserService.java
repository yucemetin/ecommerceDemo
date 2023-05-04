package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.user.UpdateUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UpdateUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface UserService {
    DataResult<List<ListUserResponse>> getAll();

    DataResult<Slice<ListUserResponse>> getAllWithPage(Pageable pageable);

    DataResult<UserDetailResponse> getById(Long id);

    DataResult<AddUserResponse> addUser(AddUserRequest addUserRequest);

    DataResult<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest);

    boolean checkIfUserIdExistsWithReturn(Long id);

    void checkIfUserIdExists(Long id);

    Result deleteUser(Long id);
}
