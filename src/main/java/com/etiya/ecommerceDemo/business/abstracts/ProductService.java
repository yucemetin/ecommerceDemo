package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.product.UpdateProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.UpdateProductResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ProductService {
    DataResult<List<ListProductResponse>> getAll();

    DataResult<Slice<ListProductResponse>> getAllWithPage(Pageable pageable);

    DataResult<ProductDetailResponse> getById(Long id) throws Exception;

    DataResult<AddProductResponse> addProduct(AddProductRequest addProductRequest) throws Exception;

    DataResult<UpdateProductResponse> updateProduct(UpdateProductRequest updateProductRequest) throws Exception;

    Result deleteProduct(Long id);
}
