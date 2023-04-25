package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier,Long> {

    Supplier findBySupplierName(String name);
}
