package com.etiya.ecommerceDemo.dataAccess.contretes;

import com.etiya.ecommerceDemo.entities.concrete.ProductVariation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariationDao extends JpaRepository<ProductVariation, Long> {
}
