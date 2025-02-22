package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.UpdateSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.UpdateSupplierResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface SupplierService {
    DataResult<List<ListSupplierResponse>> getAll();

    DataResult<Slice<ListSupplierResponse>> getAllWithPage(Pageable pageable);

    DataResult<SupplierDetailResponse> getById(Long id) throws Exception;

    DataResult<AddSupplierResponse> addSupplier(AddSupplierRequest addSupplierRequest) throws Exception;

    DataResult<UpdateSupplierResponse> updateSupplier(UpdateSupplierRequest updateSupplierRequest) throws Exception;

    Result deleteSupplier(Long id);
}
