package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();

    void addProduct(Product product);
}
