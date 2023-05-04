package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.SupplierService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.UpdateSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.UpdateSupplierResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessResult;
import com.etiya.ecommerceDemo.entities.concretes.Supplier;
import com.etiya.ecommerceDemo.repositories.abstracts.SupplierDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierManager implements SupplierService {

    private SupplierDao supplierDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Override
    public DataResult<List<ListSupplierResponse>> getAll() {
        return new SuccessDataResult<>(supplierDao.getAll(), messageService.getMessage(Messages.Supplier.successListSupplier));
    }

    @Override
    public DataResult<Slice<ListSupplierResponse>> getAllWithPage(Pageable pageable) {
        return new SuccessDataResult<>(supplierDao.getAllWithPage(pageable), messageService.getMessage(Messages.Supplier.successListSupplier));
    }

    @Override
    public DataResult<SupplierDetailResponse> getById(Long id) throws Exception {
        checkIfSupplierIdExists(id);

        return new SuccessDataResult<>(supplierDao.getOneSupplierById(id), messageService.getMessage(Messages.Supplier.successOneSupplier));
    }

    @Override
    public DataResult<AddSupplierResponse> addSupplier(AddSupplierRequest addSupplierRequest) {

        checkIfSupplierNameExists(addSupplierRequest.getSupplierName());

        Supplier supplier = this.modelMapperService.getMapper().map(addSupplierRequest, Supplier.class);
        supplierDao.save(supplier);

        AddSupplierResponse addSupplierResponse = this.modelMapperService.getMapper().map(supplier, AddSupplierResponse.class);

        return new SuccessDataResult<>(addSupplierResponse, messageService.getMessage(Messages.Supplier.successAddSupplier));
    }

    @Override
    public DataResult<UpdateSupplierResponse> updateSupplier(UpdateSupplierRequest updateSupplierRequest) {

        checkIfSupplierIdExists(updateSupplierRequest.getId());
        checkIfSupplierNameExists(updateSupplierRequest.getSupplierName());

        Supplier supplier = modelMapperService.getMapper().map(updateSupplierRequest, Supplier.class);
        supplierDao.save(supplier);

        UpdateSupplierResponse updateSupplierResponse = this.modelMapperService.getMapper().map(supplier, UpdateSupplierResponse.class);


        return new SuccessDataResult<>(updateSupplierResponse, messageService.getMessage(Messages.Supplier.successUpdateSupplier));
    }

    @Override
    public Result deleteSupplier(Long id) {
        checkIfSupplierIdExists(id);
        supplierDao.deleteById(id);
        return new SuccessResult(messageService.getMessage(Messages.Supplier.successDeleteSupplier));
    }

    public void checkIfSupplierIdExists(Long id) {
        if (!supplierDao.existsById(id)) {
            throw new NotFoundException(messageService.getMessage(Messages.Supplier.errorOneSupplier));
        }
    }

    public void checkIfSupplierNameExists(String supplierName) {
        if (supplierDao.existsSupplierBySupplierName(supplierName)) {
            throw new BusinessException(messageService.getMessage(Messages.Supplier.existsSupplierName));
        }
    }
}
