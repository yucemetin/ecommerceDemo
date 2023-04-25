package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.ProductService;
import com.etiya.ecommerceDemo.entities.concretes.Product;
import com.etiya.ecommerceDemo.repositories.abstracts.ProductDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;
    @Override
    public List<Product> getAll() {
        return productDao.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productDao.save(product);
    }
}
