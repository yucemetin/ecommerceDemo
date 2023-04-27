package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<ListProductResponse> getAll();

    ProductDetailResponse getById(Long id);

    AddProductResponse addProduct(AddProductRequest addProductRequest);
}
