package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.ProductService;
import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.entities.concretes.Product;
import com.etiya.ecommerceDemo.repositories.abstracts.ProductDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;
    private ModelMapperService modelMapperService;

    @Override
    public List<ListProductResponse> getAll() {
        return productDao.getAll();
    }

    @Override
    public ProductDetailResponse getById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {

        Product product = this.modelMapperService.getMapper().map(addProductRequest, Product.class);

        productDao.save(product);

        return this.modelMapperService.getMapper().map(product, AddProductResponse.class);
    }
}
