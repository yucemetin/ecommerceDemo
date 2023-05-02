package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperManager;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.User;
import com.etiya.ecommerceDemo.repositories.abstracts.OrderDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
    private UserManager userManager;
    private OrderManager orderManager;

    public ResourceBundleMessageSource getBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @BeforeEach
    void setUp() {
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        orderDao = mock(OrderDao.class);

        orderManager = new OrderManager(orderDao, modelMapperService, messageSource, userManager);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        List<ListOrderResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListOrderResponse(1L, new Date(), 3L));
        fakeData.add(new ListOrderResponse(2L, new Date(), 5L));

        DataResult<List<ListOrderResponse>> fakeResult = new SuccessDataResult<>(fakeData, messageSource.getMessage(Messages.Order.successListOrder, null, LocaleContextHolder.getLocale()));

        when(orderDao.getAll()).thenReturn(fakeResult.getData());

        DataResult<List<ListOrderResponse>> expectedResult = orderManager.getAll();

        assert expectedResult.getData().equals(fakeResult.getData());
    }

    @Test
    void getById() throws Exception {
        when(orderDao.existsById(any())).thenReturn(true);

        DataResult<OrderDetailResponse> fakeResult = new SuccessDataResult<>(new OrderDetailResponse(1L, new Date(), 4L), messageSource.getMessage(Messages.Order.successListOrder, null, LocaleContextHolder.getLocale()));

        when(orderDao.getOneOrder(1L)).thenReturn(fakeResult.getData());

        DataResult<OrderDetailResponse> expectedResult = orderManager.getById(1L);

        assert expectedResult.getData().equals(fakeResult.getData());
    }

    @Test
    void addOrder() {
        when(userManager.checkIfUserIdExistsWithReturn(any())).thenReturn(true);

        AddOrderRequest addOrderRequest = new AddOrderRequest(new Date(), 2L);

        DataResult<AddOrderResponse> actualResult = orderManager.addOrder(addOrderRequest);
        actualResult.getData().setId(1L);

        DataResult<AddOrderResponse> expectedResult = new SuccessDataResult<>(new AddOrderResponse(1L, new Date(), new User(2L, "Metin", "Yüce", "metin@hotmail.com", "1231233123", null, null, null)));

        assert actualResult.getData().equals(expectedResult.getData());
    }

    @Test
    void updateOrder() {
    }
}