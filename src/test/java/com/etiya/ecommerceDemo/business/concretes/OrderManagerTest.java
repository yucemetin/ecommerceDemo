package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.OrderService;
import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.order.UpdateOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.UpdateOrderResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageManager;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperManager;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.OrderDao;
import com.etiya.ecommerceDemo.repositories.abstracts.UserDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OrderManagerTest {

    private OrderDao orderDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;
    private UserDao userDao;
    private MessageService messageService;
    private UserService userService;
    private OrderService orderService;

    public ResourceBundleMessageSource getBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @BeforeEach
    void setUp() {
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        messageService = new MessageManager(messageSource);
        orderDao = mock(OrderDao.class);
        userDao = mock(UserDao.class);

        userService = new UserManager(userDao, modelMapperService, messageService);
        orderService = new OrderManager(orderDao, modelMapperService, messageService, userService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        List<ListOrderResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListOrderResponse(1L, new Date(), 3L));
        fakeData.add(new ListOrderResponse(2L, new Date(), 5L));

        DataResult<List<ListOrderResponse>> fakeResult = new SuccessDataResult<>(fakeData, messageService.getMessage(Messages.Order.successListOrder));

        when(orderDao.getAll()).thenReturn(fakeResult.getData());

        DataResult<List<ListOrderResponse>> expectedResult = orderService.getAll();

        assert expectedResult.getData().equals(fakeResult.getData());
    }

    @Test
    void getById() throws Exception {
        when(orderDao.existsById(any())).thenReturn(true);

        DataResult<OrderDetailResponse> fakeResult = new SuccessDataResult<>(new OrderDetailResponse(1L, new Date(), 4L), messageService.getMessage(Messages.Order.successListOrder));

        when(orderDao.getOneOrder(1L)).thenReturn(fakeResult.getData());

        DataResult<OrderDetailResponse> expectedResult = orderService.getById(1L);

        assert expectedResult.getData().equals(fakeResult.getData());
    }

    @Test
    void addOrder() {
        when(userDao.existsById(any())).thenReturn(true);

        AddOrderRequest addOrderRequest = new AddOrderRequest(new Date(), 1L);

        DataResult<AddOrderResponse> actualResult = orderService.addOrder(addOrderRequest);
        actualResult.getData().setId(1L);

        DataResult<AddOrderResponse> expectedResult = new SuccessDataResult<>(new AddOrderResponse(1L, new Date(), new User(1L, "Metin", "Yüce", "metin@hotmail.com", "1231233123", null, null, null)));

        assert actualResult.getData().getId().equals(expectedResult.getData().getId());
    }

    @Test
    void updateOrder() throws Exception {
        when(userDao.existsById(any())).thenReturn(true);
        when(orderDao.existsById(any())).thenReturn(true);

        UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest(1L, 3L);

        DataResult<UpdateOrderResponse> actualResult = new SuccessDataResult<>(new UpdateOrderResponse(1L, new Date(), 3L));
        DataResult<UpdateOrderResponse> expectedResult = orderService.updateOrder(updateOrderRequest);

        assert actualResult.getData().getId().equals(expectedResult.getData().getId()) && actualResult.getData().getUserId().equals(expectedResult.getData().getUserId());

    }

    @Test
    void deleteWithNonExistsIdShouldThrowException() {
        when(orderDao.existsById(1L)).thenReturn(false);
        when(orderDao.existsById(2L)).thenReturn(true);

        assertThrows(NotFoundException.class, () -> {
            orderService.deleteOrder(1L);
        });

    }
}