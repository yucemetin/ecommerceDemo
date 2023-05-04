package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.UpdateSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.UpdateSupplierResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageManager;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperManager;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.repositories.abstracts.SupplierDao;
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

class SupplierManagerTest {

    private SupplierDao supplierDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;
    private MessageService messageService;

    private SupplierManager supplierManager;

    public ResourceBundleMessageSource getBundleMessageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    @BeforeEach
    void setUp() {
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = getBundleMessageSource();
        supplierDao = mock(SupplierDao.class);
        messageService = new MessageManager(messageSource);

        supplierManager = new SupplierManager(supplierDao, modelMapperService, messageService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAll() {
        List<ListSupplierResponse> fakeData = new ArrayList<>();
        fakeData.add(new ListSupplierResponse(1L, "Trendyol"));
        fakeData.add(new ListSupplierResponse(2L, "Migros"));

        when(supplierDao.getAll()).thenReturn(fakeData);

        DataResult<List<ListSupplierResponse>> actualResult = new SuccessDataResult<>(fakeData);

        DataResult<List<ListSupplierResponse>> expectedResult = supplierManager.getAll();

        assert actualResult.getData().equals(expectedResult.getData());
    }

    @Test
    void getById() throws Exception {

        SupplierDetailResponse supplierDetailResponse = new SupplierDetailResponse(1L, "Trendyol");
        when(supplierDao.existsById(any())).thenReturn(true);
        when(supplierDao.getOneSupplierById(1L)).thenReturn(supplierDetailResponse);

        DataResult<SupplierDetailResponse> actualResult = new SuccessDataResult<>(supplierDetailResponse);

        DataResult<SupplierDetailResponse> expectedResult = supplierManager.getById(1L);

        assert actualResult.getData().equals(expectedResult.getData());
    }

    @Test
    void addSupplier() {
        when(supplierDao.existsSupplierBySupplierName(any())).thenReturn(false);
        AddSupplierRequest addSupplierRequest = new AddSupplierRequest("Trendyol");

        DataResult<AddSupplierResponse> actualResult = supplierManager.addSupplier(addSupplierRequest);
        actualResult.getData().setId(1L);

        DataResult<AddSupplierResponse> expectedData = new SuccessDataResult<>(new AddSupplierResponse(1L, "Trendyol"));

        assert actualResult.getData().equals(expectedData.getData());
    }

    @Test
    void updateSupplier() {
        when(supplierDao.existsSupplierBySupplierName(any())).thenReturn(false);
        when(supplierDao.existsById(any())).thenReturn(true);

        UpdateSupplierRequest updateSupplierRequest = new UpdateSupplierRequest(1L, "Trendyol");

        DataResult<UpdateSupplierResponse> actualResult = supplierManager.updateSupplier(updateSupplierRequest);

        DataResult<UpdateSupplierResponse> expectedData = new SuccessDataResult<>(new UpdateSupplierResponse(1L, "Trendyol"));

        assert actualResult.getData().equals(expectedData.getData());
    }

    @Test
    void deleteWithNonExistsIdShouldThrowException() {
        when(supplierDao.existsById(1L)).thenReturn(false);

        assertThrows(NotFoundException.class, () -> {
            supplierManager.deleteSupplier(1L);
        });

    }
}