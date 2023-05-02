package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.category.UpdateCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.UpdateCategoryResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.NotFoundException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperManager;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.repositories.abstracts.CategoryDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryManagerTest {
    CategoryDao categoryDao;
    ModelMapperService modelMapperService;
    MessageSource messageSource;
    CategoryManager categoryManager;

    public ResourceBundleMessageSource getBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @BeforeEach
    void setUp() {
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        categoryDao = mock(CategoryDao.class);

        categoryManager = new CategoryManager(categoryDao, modelMapperService, messageSource);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCategory() {
        when(categoryDao.existsCategoriesByName(any())).thenReturn(false);
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("Giyim");

        DataResult<AddCategoryResponse> actualResponse = categoryManager.addCategory(addCategoryRequest);
        actualResponse.getData().setId(1L);
        SuccessDataResult<AddCategoryResponse> expectedResponse = new SuccessDataResult<>(new AddCategoryResponse(1L, "Giyim"));

        assert actualResponse.getData().equals(expectedResponse.getData());

    }

    @Test
    void getAll() {
        List<ListCategoryResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListCategoryResponse(1L, "Giyim"));
        fakeData.add(new ListCategoryResponse(2L, "Deneme"));

        DataResult<List<ListCategoryResponse>> fakeResult = new SuccessDataResult<>(fakeData, messageSource.getMessage(Messages.Category.successListCategory, null, LocaleContextHolder.getLocale()));

        when(categoryDao.getAll()).thenReturn(fakeResult.getData());

        DataResult<List<ListCategoryResponse>> result = categoryManager.getAll();

        assert result.getData().size() == fakeResult.getData().size();
    }

    @Test
    void updateWithNonExistingIdShouldThrowException() {
        when(categoryDao.existsById(1L)).thenReturn(false);

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(1L, "Giyim");


        assertThrows(NotFoundException.class, () -> {
            categoryManager.updateCategory(updateCategoryRequest);
        });
    }

    @Test
    void updateWithSameNameShouldThrowException() {
        when(categoryDao.existsById(any())).thenReturn(true);
        when(categoryDao.existsCategoriesByName(any())).thenReturn(true);

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(1L, "Giyim");

        assertThrows(BusinessException.class, () -> {
            categoryManager.updateCategory(updateCategoryRequest);
        });
    }

    @Test
    void update() {
        when(categoryDao.existsById(any())).thenReturn(true);
        when(categoryDao.existsCategoriesByName(any())).thenReturn(false);

        UpdateCategoryRequest updateCategoryRequest = new UpdateCategoryRequest(1L, "Giyim");
        DataResult<UpdateCategoryResponse> actualResponse = categoryManager.updateCategory(updateCategoryRequest);

        SuccessDataResult<UpdateCategoryResponse> expectedResponse =
                new SuccessDataResult<>(new UpdateCategoryResponse(1L, "Giyim"));

        assert actualResponse.getData().equals(expectedResponse.getData());
    }
}