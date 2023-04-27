package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.ProductService;
import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
@AllArgsConstructor
public class ProductsController {

    private ProductService productService;

    @GetMapping
    public List<ListProductResponse> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDetailResponse getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping
    public AddProductResponse addProduct(@RequestBody AddProductRequest addProductRequest) {
        return productService.addProduct(addProductRequest);
    }
}
