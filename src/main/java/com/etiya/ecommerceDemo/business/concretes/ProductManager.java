package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.abstracts.ProductService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.product.AddProductRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.product.UpdateProductRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.AddProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ListProductResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.ProductDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.product.UpdateProductResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.Category;
import com.etiya.ecommerceDemo.entities.concretes.Product;
import com.etiya.ecommerceDemo.repositories.abstracts.ProductDao;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {

    private ProductDao productDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;
    private CategoryService categoryService;

    @Override
    public DataResult<List<ListProductResponse>> getAll() {
        return new SuccessDataResult<>(productDao.getAll(), messageSource.getMessage(Messages.Product.successListProduct, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<ProductDetailResponse> getById(Long id) throws Exception {
        checkIfProductIdExists(id);

        return new SuccessDataResult<>(productDao.getProductById(id), messageSource.getMessage(Messages.Product.successOneProduct, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<AddProductResponse> addProduct(AddProductRequest addProductRequest) throws Exception {

        categoryService.checkIfCategoryIdExists(addProductRequest.getCategoryId());

        Product product = this.modelMapperService.getMapper().map(addProductRequest, Product.class);
        productDao.save(product);

        AddProductResponse addProductResponse = this.modelMapperService.getMapper().map(product, AddProductResponse.class);
        return new SuccessDataResult<>(addProductResponse, messageSource.getMessage(Messages.Product.successAddProduct, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<UpdateProductResponse> updateProduct(UpdateProductRequest updateProductRequest) throws Exception {

        checkIfProductIdExists(updateProductRequest.getId());
        categoryService.checkIfCategoryIdExists(updateProductRequest.getCategoryId());

        Product product = modelMapperService.getMapper().map(updateProductRequest, Product.class);
        productDao.save(product);

        UpdateProductResponse updateProductResponse = this.modelMapperService.getMapper().map(product, UpdateProductResponse.class);


        return new SuccessDataResult<>(updateProductResponse, messageSource.getMessage(Messages.Product.successUpdateProduct, null, LocaleContextHolder.getLocale()));
    }

    public void checkIfProductIdExists(Long id) {
        if (!productDao.existsById(id)) {
            throw new BusinessException(messageSource.getMessage(Messages.Product.errorOneProduct, null, LocaleContextHolder.getLocale()));
        }
    }
}
