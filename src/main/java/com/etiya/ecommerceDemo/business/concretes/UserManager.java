package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.user.UpdateUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UpdateUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessResult;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserDao userDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Override
    public DataResult<List<ListUserResponse>> getAll() {
        return new SuccessDataResult<>(userDao.getAll(), messageService.getMessage(Messages.User.successListUser));
    }

    @Override
    public DataResult<Slice<ListUserResponse>> getAllWithPage(Pageable pageable) {
        return new SuccessDataResult<>(userDao.getAllWithPage(pageable), messageService.getMessage(Messages.User.successListUser));
    }

    @Override
    public DataResult<UserDetailResponse> getById(Long id) {
        checkIfUserIdExists(id);

        return new SuccessDataResult<>(userDao.getUserById(id), messageService.getMessage(Messages.User.successOneUser));

    }

    @Override
    public DataResult<AddUserResponse> addUser(AddUserRequest addUserRequest) {

        checkIfEmailExists(addUserRequest.getEmail());

        User user = modelMapperService.getMapper().map(addUserRequest, User.class);

        userDao.save(user);
        AddUserResponse addUserResponse = this.modelMapperService.getMapper().map(user, AddUserResponse.class);

        return new SuccessDataResult<>(addUserResponse, messageService.getMessage(Messages.User.successAddUser));
    }

    @Override
    public DataResult<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest) {

        checkIfUserIdExists(updateUserRequest.getId());
        checkIfEmailExists(updateUserRequest.getEmail());

        User user = modelMapperService.getMapper().map(updateUserRequest, User.class);

        userDao.save(user);

        UpdateUserResponse updateUserResponse = this.modelMapperService.getMapper().map(user, UpdateUserResponse.class);

        return new SuccessDataResult<>(updateUserResponse, messageService.getMessage(Messages.User.successUpdateUser));
    }

    public boolean checkIfUserIdExistsWithReturn(Long id) {
        return userDao.existsById(id);
    }

    public void checkIfUserIdExists(Long id) {
        if (!userDao.existsById(id)) {
            throw new NotFoundException(messageService.getMessage(Messages.User.errorOneUser));
        }
    }

    @Override
    public Result deleteUser(Long id) {
        checkIfUserIdExists(id);
        userDao.deleteById(id);
        return new SuccessResult(messageService.getMessage(Messages.User.successDeleteUser));
    }

    public void checkIfEmailExists(String email) {
        if (userDao.existsUserByEmail(email)) {
            throw new BusinessException(messageService.getMessage(Messages.User.existsEmail));
        }
    }
}
