package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;

import java.util.List;

public interface CategoryService {

    DataResult<List<ListCategoryResponse>> getAll();

    DataResult<CategoryDetailResponse> getById(Long id);

    DataResult<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) throws Exception;
}
