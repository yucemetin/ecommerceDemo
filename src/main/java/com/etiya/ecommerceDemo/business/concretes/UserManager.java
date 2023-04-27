package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserDao userDao;
    private ModelMapperService modelMapperService;

    @Override
    public List<ListUserResponse> getAll() {
        return userDao.getAll();
    }

    @Override
    public UserDetailResponse getById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public AddUserResponse addUser(AddUserRequest addUserRequest) {

        if (userDao.existsUserByEmail(addUserRequest.getEmail())) {
            throw new BusinessException("Girdiğiniz email zaten mevcut");
        }

        User user = modelMapperService.getMapper().map(addUserRequest, User.class);

        userDao.save(user);
        AddUserResponse addUserResponse = this.modelMapperService.getMapper().map(user, AddUserResponse.class);

        return addUserResponse;
    }
}
