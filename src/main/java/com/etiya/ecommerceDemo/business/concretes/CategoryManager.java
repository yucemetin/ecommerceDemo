package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.category.UpdateCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.UpdateCategoryResponse;
import com.etiya.ecommerceDemo.core.exceptions.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.NotFoundException;
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
        return new SuccessDataResult<>(categoryDao.getAll(), messageSource.getMessage(Messages.Category.successListCategory, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<CategoryDetailResponse> getById(Long id) throws Exception {
        checkIfCategoryIdExists(id);

        return new SuccessDataResult<>(categoryDao.getCategoryById(id), messageSource.getMessage(Messages.Category.successOneCategory, null, LocaleContextHolder.getLocale()));
    }

    @Override
    public DataResult<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) {

        checkIfCategoryNameExists(addCategoryRequest.getName());

        Category category = this.modelMapperService.getMapper().map(addCategoryRequest, Category.class);

        categoryDao.save(category);

        AddCategoryResponse addCategoryResponse = this.modelMapperService.getMapper().map(category, AddCategoryResponse.class);

        return new SuccessDataResult<>(addCategoryResponse, messageSource.getMessage(Messages.Category.successAddCategory, null, LocaleContextHolder.getLocale()));

    }

    @Override
    public DataResult<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        checkIfCategoryIdExists(updateCategoryRequest.getId());
        checkIfCategoryNameExists(updateCategoryRequest.getName());

        Category category = modelMapperService.getMapper().map(updateCategoryRequest, Category.class);
        categoryDao.save(category);

        UpdateCategoryResponse updateCategoryResponse = this.modelMapperService.getMapper().map(category, UpdateCategoryResponse.class);

        return new SuccessDataResult<>(updateCategoryResponse, messageSource.getMessage(Messages.Category.successUpdateCategory, null, LocaleContextHolder.getLocale()));
    }

    public void checkIfCategoryNameExists(String categoryName) {
        if (categoryDao.existsCategoriesByName(categoryName)) {
            throw new BusinessException(messageSource.getMessage(Messages.Category.existsCategoryName, null, LocaleContextHolder.getLocale()));
        }
    }

    public void checkIfCategoryIdExists(Long id) {
        if (!categoryDao.existsById(id)) {
            throw new NotFoundException(messageSource.getMessage(Messages.Category.errorOneCategory, null, LocaleContextHolder.getLocale()));
        }
    }
}
