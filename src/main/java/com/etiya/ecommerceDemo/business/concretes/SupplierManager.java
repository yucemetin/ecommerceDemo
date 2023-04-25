package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.SupplierService;
import com.etiya.ecommerceDemo.entities.concretes.Supplier;
import com.etiya.ecommerceDemo.repositories.abstracts.SupplierDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SupplierManager implements SupplierService {

    private SupplierDao supplierDao;

    @Override
    public List<Supplier> getAll() {
        return supplierDao.findAll();
    }

    @Override
    public Supplier getById(Long id) {
        return supplierDao.findById(id).orElseThrow();
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierDao.save(supplier);
    }
}
