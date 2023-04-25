package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAll();

    Supplier getById(Long id);

    void addSupplier(Supplier supplier) throws Exception;
}
