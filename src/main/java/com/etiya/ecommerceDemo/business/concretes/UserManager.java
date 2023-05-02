package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.user.UpdateUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UpdateUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.NotFoundException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private UserDao userDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;

    @Override
    public DataResult<List<ListUserResponse>> getAll() {
        return new SuccessDataResult<>(userDao.getAll(), messageSource.getMessage(Messages.User.successListUser, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<UserDetailResponse> getById(Long id) {
        checkIfUserIdExists(id);

        return new SuccessDataResult<>(userDao.getUserById(id), messageSource.getMessage(Messages.User.successOneUser, null, LocaleContextHolder.getLocale()));

    }

    @Override
    public DataResult<AddUserResponse> addUser(AddUserRequest addUserRequest) {

        checkIfEmailExists(addUserRequest.getEmail());

        User user = modelMapperService.getMapper().map(addUserRequest, User.class);

        userDao.save(user);
        AddUserResponse addUserResponse = this.modelMapperService.getMapper().map(user, AddUserResponse.class);

        return new SuccessDataResult<>(addUserResponse, messageSource.getMessage(Messages.User.successAddUser, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<UpdateUserResponse> updateUser(UpdateUserRequest updateUserRequest) {

        checkIfUserIdExists(updateUserRequest.getId());
        checkIfEmailExists(updateUserRequest.getEmail());

        User user = modelMapperService.getMapper().map(updateUserRequest, User.class);

        userDao.save(user);

        UpdateUserResponse updateUserResponse = this.modelMapperService.getMapper().map(user, UpdateUserResponse.class);

        return new SuccessDataResult<>(updateUserResponse, messageSource.getMessage(Messages.User.successUpdateUser, null, LocaleContextHolder.getLocale()));
    }

    public void checkIfUserIdExists(Long id) {
        if (!userDao.existsById(id)) {
            throw new NotFoundException(messageSource.getMessage(Messages.User.errorOneUser, null, LocaleContextHolder.getLocale()));
        }
    }

    public void checkIfEmailExists(String email) {
        if (userDao.existsUserByEmail(email)) {
            throw new BusinessException(messageSource.getMessage(Messages.User.existsEmail, null, LocaleContextHolder.getLocale()));
        }
    }
}
