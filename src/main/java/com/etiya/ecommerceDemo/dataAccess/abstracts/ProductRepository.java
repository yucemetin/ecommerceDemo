package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query(value = "SELECT p.* FROM products p INNER JOIN order_details od ON od.product_id = p.product_id", nativeQuery = true)
    List<Product> getProductByOrdered();
}
