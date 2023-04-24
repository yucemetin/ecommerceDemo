package com.etiya.ecommerceDemo.repository.contretes;

import com.etiya.ecommerceDemo.entities.concrete.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierDao extends JpaRepository<Supplier,Long> {
}
