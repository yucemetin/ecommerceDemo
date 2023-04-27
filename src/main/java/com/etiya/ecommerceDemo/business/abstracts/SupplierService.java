package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import com.etiya.ecommerceDemo.entities.concretes.Supplier;

import java.util.List;

public interface SupplierService {
    List<ListSupplierResponse> getAll();

    SupplierDetailResponse getById(Long id);

    AddSupplierResponse addSupplier(AddSupplierRequest addSupplierRequest) throws Exception;
}
