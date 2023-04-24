package com.etiya.ecommerceDemo.repository.contretes;

import com.etiya.ecommerceDemo.entities.concrete.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeDao extends JpaRepository<ProductAttribute, Long> {
}
