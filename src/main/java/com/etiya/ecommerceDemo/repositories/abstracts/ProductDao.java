package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT p.* FROM products p INNER JOIN order_details od ON od.product_id = p.id", nativeQuery = true)
    List<Product> getProductByOrdered();
}
