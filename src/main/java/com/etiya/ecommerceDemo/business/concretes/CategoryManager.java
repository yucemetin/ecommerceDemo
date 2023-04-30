package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.Category;
import com.etiya.ecommerceDemo.repositories.abstracts.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;

    @Override
    public DataResult<List<ListCategoryResponse>> getAll() {
        return new SuccessDataResult<>(categoryDao.getAll(), messageSource.getMessage("successListCategory",null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<CategoryDetailResponse> getById(Long id) {
        if (categoryDao.getCategoryById(id) == null) {
            return new SuccessDataResult<>(categoryDao.getCategoryById(id), messageSource.getMessage("errorOneCategory",null, LocaleContextHolder.getLocale()));
        }
        return new SuccessDataResult<>(categoryDao.getCategoryById(id), messageSource.getMessage("successOneCategory",null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) {

        if (categoryDao.existsCategoriesByName(addCategoryRequest.getName())) {
            throw new BusinessException(messageSource.getMessage("existsCategoryName",null, LocaleContextHolder.getLocale()));
        }

        Category category = this.modelMapperService.getMapper().map(addCategoryRequest, Category.class);

        categoryDao.save(category);

        AddCategoryResponse addCategoryResponse = this.modelMapperService.getMapper().map(category, AddCategoryResponse.class);

        return new SuccessDataResult<>(addCategoryResponse, messageSource.getMessage("successAddCategory",null, LocaleContextHolder.getLocale()));

    }
}
