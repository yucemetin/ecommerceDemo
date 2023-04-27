package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.entities.concretes.Category;

import java.util.List;

public interface CategoryService {

    List<ListCategoryResponse> getAll();

    CategoryDetailResponse getById(Long id);

    AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) throws Exception;
}
