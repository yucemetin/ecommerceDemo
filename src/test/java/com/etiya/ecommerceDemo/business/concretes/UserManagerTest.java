package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.user.AddUserRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.user.UpdateUserRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.user.AddUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.ListUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UpdateUserResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.user.UserDetailResponse;
import com.etiya.ecommerceDemo.core.internationalization.MessageManager;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperManager;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserManagerTest {

    private UserDao userDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;
    private MessageService messageService;

    private UserManager userManager;

    public ResourceBundleMessageSource getBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @BeforeEach
    void setUp() {
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        userDao = mock(UserDao.class);
        messageService = new MessageManager(messageSource);
        userManager = new UserManager(userDao, modelMapperService, messageService);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {

        List<ListUserResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListUserResponse(1L, "Metin", "Yüce", "metin@hotmail.com", "123123"));
        fakeData.add(new ListUserResponse(2L, "Ayse", "Isik", "ayse@hotmail.com", "abcdefg"));

        when(userDao.getAll()).thenReturn(fakeData);

        DataResult<List<ListUserResponse>> fakeResult = new SuccessDataResult<>(fakeData, messageService.getMessage(Messages.User.successListUser));

        DataResult<List<ListUserResponse>> expectedResult = userManager.getAll();

        assert fakeResult.getData().equals(expectedResult.getData());
    }

    @Test
    void getById() {
        when(userDao.existsById(any())).thenReturn(true);
        when(userDao.getUserById(1L)).thenReturn(new UserDetailResponse(1L, "Metin", "Yüce", "metin@hotmail.com", "123123"));

        DataResult<UserDetailResponse> actualResult = new SuccessDataResult<>(new UserDetailResponse(1L, "Metin", "Yüce", "metin@hotmail.com", "123123"), messageService.getMessage(Messages.User.successOneUser));
        DataResult<UserDetailResponse> expectedResult = userManager.getById(1L);

        assert actualResult.getData().equals(expectedResult.getData());

    }

    @Test
    void addUser() {
        when(userDao.existsUserByEmail(any())).thenReturn(false);

        AddUserRequest addUserRequest = new AddUserRequest("Metin", "Yüce", "metin@gmail.com", "123123");

        DataResult<AddUserResponse> actualResult = userManager.addUser(addUserRequest);
        actualResult.getData().setId(1L);

        DataResult<AddUserResponse> expectedResult = new SuccessDataResult<>(new AddUserResponse(1L, "Metin", "Yüce", "metin@gmail.com", "123123"));

        assert actualResult.getData().equals(expectedResult.getData());

    }

    @Test
    void updateUser() {
        when(userDao.existsById(any())).thenReturn(true);
        when(userDao.existsUserByEmail(any())).thenReturn(false);

        UpdateUserRequest updateUserRequest = new UpdateUserRequest(1L, "Metin", "Yüce", "metin@gmail.com", "123123");

        DataResult<UpdateUserResponse> actualResult = userManager.updateUser(updateUserRequest);

        DataResult<UpdateUserResponse> expectedResult = new SuccessDataResult<>(new UpdateUserResponse(1L, "Metin", "Yüce", "metin@gmail.com", "123123"));

        assert actualResult.getData().equals(expectedResult.getData());
    }
}