package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.entities.concretes.Category;
import com.etiya.ecommerceDemo.repositories.abstracts.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;
    private ModelMapperService modelMapperService;

    @Override
    public List<ListCategoryResponse> getAll() {
        return categoryDao.getAll();
    }

    @Override
    public CategoryDetailResponse getById(Long id) {
        return categoryDao.getCategoryById(id);
    }

    @Override
    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {

        if (categoryDao.existsCategoriesByName(addCategoryRequest.getName())) {
            throw new BusinessException("Girdiğiniz kategori zaten mevcut");
        }

        Category category = this.modelMapperService.getMapper().map(addCategoryRequest, Category.class);

        categoryDao.save(category);

        AddCategoryResponse addCategoryResponse = this.modelMapperService.getMapper().map(category, AddCategoryResponse.class);

        return addCategoryResponse;

    }
}
