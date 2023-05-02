package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.SupplierService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.UpdateSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.UpdateSupplierResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.NotFoundException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.Supplier;
import com.etiya.ecommerceDemo.repositories.abstracts.SupplierDao;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierManager implements SupplierService {

    private SupplierDao supplierDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;

    @Override
    public DataResult<List<ListSupplierResponse>> getAll() {
        return new SuccessDataResult<>(supplierDao.getAll(), messageSource.getMessage(Messages.Supplier.successListSupplier, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<SupplierDetailResponse> getById(Long id) throws Exception {
        checkIfSupplierIdExists(id);

        return new SuccessDataResult<>(supplierDao.getOneSupplierById(id), messageSource.getMessage(Messages.Supplier.successOneSupplier, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<AddSupplierResponse> addSupplier(AddSupplierRequest addSupplierRequest) {

        checkIfSupplierNameExists(addSupplierRequest.getSupplierName());

        Supplier supplier = this.modelMapperService.getMapper().map(addSupplierRequest, Supplier.class);
        supplierDao.save(supplier);

        AddSupplierResponse addSupplierResponse = this.modelMapperService.getMapper().map(supplier, AddSupplierResponse.class);

        return new SuccessDataResult<>(addSupplierResponse, messageSource.getMessage(Messages.Supplier.successAddSupplier, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<UpdateSupplierResponse> updateSupplier(UpdateSupplierRequest updateSupplierRequest) {

        checkIfSupplierIdExists(updateSupplierRequest.getId());
        checkIfSupplierNameExists(updateSupplierRequest.getSupplierName());

        Supplier supplier = modelMapperService.getMapper().map(updateSupplierRequest, Supplier.class);
        supplierDao.save(supplier);

        UpdateSupplierResponse updateSupplierResponse = this.modelMapperService.getMapper().map(supplier, UpdateSupplierResponse.class);


        return new SuccessDataResult<>(updateSupplierResponse, messageSource.getMessage(Messages.Supplier.successUpdateSupplier, null, LocaleContextHolder.getLocale()));
    }

    public void checkIfSupplierIdExists(Long id) {
        if (!supplierDao.existsById(id)) {
            throw new NotFoundException(messageSource.getMessage(Messages.Supplier.errorOneSupplier, null, LocaleContextHolder.getLocale()));
        }
    }

    public void checkIfSupplierNameExists(String supplierName) {
        if (supplierDao.existsSupplierBySupplierName(supplierName)) {
            throw new BusinessException(messageSource.getMessage(Messages.Supplier.existsSupplierName, null, LocaleContextHolder.getLocale()));
        }
    }
}
