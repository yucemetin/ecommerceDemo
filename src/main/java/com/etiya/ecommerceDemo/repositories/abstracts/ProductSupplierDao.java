package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierDao extends JpaRepository<ProductSupplier,Integer> {
}
