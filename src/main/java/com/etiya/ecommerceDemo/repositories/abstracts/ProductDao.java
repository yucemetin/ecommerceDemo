package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Long> {

    @Query(value = "select new" +
            " com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse(p.id,p.name,p.unitPrice,p.category.name)" +
            " from Product p where p.id = :id", nativeQuery = false)
    ProductDetailResponse getProductById(Long id);

    @Query(value = "select new" +
            "  com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse(p.id,p.name,p.unitPrice,p.category.name)" +
            " from Product p", nativeQuery = false)
    List<ListProductResponse> getAll();
}
