package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.ProductService;
import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
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
    public DataResult<List<ListProductResponse>> getAll() {
        return new SuccessDataResult<>(productDao.getAll(), "Ürünler başarılı bir şekilde listelendi.");
    }

    @Override
    public DataResult<ProductDetailResponse> getById(Long id) {
        if (productDao.getProductById(id) == null) {
            return new SuccessDataResult<>(productDao.getProductById(id), "Ürün bulunamadı.");
        }
        return new SuccessDataResult<>(productDao.getProductById(id), "Ürün başarılı bir şekilde listelendi.");
    }

    @Override
    public DataResult<AddProductResponse> addProduct(AddProductRequest addProductRequest) {

        Product product = this.modelMapperService.getMapper().map(addProductRequest, Product.class);
        productDao.save(product);

        AddProductResponse addProductResponse = this.modelMapperService.getMapper().map(product, AddProductResponse.class);
        return new SuccessDataResult<>(addProductResponse, "Ürün başarılı bir şekilde eklendi.");
    }
}
