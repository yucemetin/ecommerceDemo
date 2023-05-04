package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.category.UpdateCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.UpdateCategoryResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.BusinessException;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessResult;
import com.etiya.ecommerceDemo.entities.concretes.Category;
import com.etiya.ecommerceDemo.repositories.abstracts.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;

    @Override
    public DataResult<List<ListCategoryResponse>> getAll() {
        return new SuccessDataResult<>(categoryDao.getAll(), messageService.getMessage(Messages.Category.successListCategory));
    }

    @Override
    public DataResult<Slice<ListCategoryResponse>> getAllWithPagination(Pageable pageable) {
        return new SuccessDataResult<>(categoryDao.getAllWithPage(pageable), messageService.getMessage(Messages.Category.successListCategory));
    }

    @Override
    public DataResult<CategoryDetailResponse> getById(Long id) throws Exception {
        checkIfCategoryIdExists(id);

        return new SuccessDataResult<>(categoryDao.getCategoryById(id), messageService.getMessage(Messages.Category.successOneCategory));
    }

    @Override
    public DataResult<AddCategoryResponse> addCategory(AddCategoryRequest addCategoryRequest) {

        checkIfCategoryNameExists(addCategoryRequest.getName());

        Category category = this.modelMapperService.getMapper().map(addCategoryRequest, Category.class);

        categoryDao.save(category);

        AddCategoryResponse addCategoryResponse = this.modelMapperService.getMapper().map(category, AddCategoryResponse.class);

        return new SuccessDataResult<>(addCategoryResponse, messageService.getMessage(Messages.Category.successAddCategory));

    }

    @Override
    public DataResult<UpdateCategoryResponse> updateCategory(UpdateCategoryRequest updateCategoryRequest) {

        checkIfCategoryNameExistsForUpdate(updateCategoryRequest.getName(), updateCategoryRequest.getId());

        Category category = modelMapperService.getMapper().map(updateCategoryRequest, Category.class);
        categoryDao.save(category);

        UpdateCategoryResponse updateCategoryResponse = this.modelMapperService.getMapper().map(category, UpdateCategoryResponse.class);

        return new SuccessDataResult<>(updateCategoryResponse, messageService.getMessage(Messages.Category.successUpdateCategory));
    }

    public void checkIfCategoryNameExists(String categoryName) {
        if (categoryDao.existsCategoriesByName(categoryName)) {
            throw new BusinessException(messageService.getMessage(Messages.Category.existsCategoryName));
        }
    }

    public void checkIfCategoryIdExists(Long id) {
        if (!categoryDao.existsById(id)) {
            throw new NotFoundException(messageService.getMessage(Messages.Category.errorOneCategory));
        }
    }

    public void checkIfCategoryNameExistsForUpdate(String categoryName, Long id) {
        if (categoryDao.findById(id).orElseThrow(() -> {
            throw new NotFoundException(messageService.getMessage(Messages.Category.errorOneCategory));
        }).getName().equals(categoryName)) {
            if (categoryDao.existsCategoriesByName(categoryName)) {
                throw new BusinessException(messageService.getMessage(Messages.Category.existsCategoryName));
            }
        }

    }

    @Override
    public Result delete(Long id) {
        this.checkIfCategoryIdExists(id);

        this.categoryDao.deleteById(id);

        return new SuccessResult(messageService.getMessage(Messages.Category.successDeleteCategory));
    }
}
