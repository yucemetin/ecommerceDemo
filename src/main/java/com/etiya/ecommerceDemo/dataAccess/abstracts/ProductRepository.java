package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
