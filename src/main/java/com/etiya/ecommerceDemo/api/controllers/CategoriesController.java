package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.business.dtos.requests.category.AddCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.category.UpdateCategoryRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.category.AddCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.CategoryDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.ListCategoryResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.category.UpdateCategoryResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@AllArgsConstructor
public class CategoriesController {

    private CategoryService categoryService;

    @GetMapping
    public DataResult<List<ListCategoryResponse>> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/page")
    public DataResult<Slice<ListCategoryResponse>> getAllWithPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return categoryService.getAllWithPagination(pageable);
    }

    @GetMapping("/{id}")
    public DataResult<CategoryDetailResponse> getById(@PathVariable Long id) throws Exception {
        return categoryService.getById(id);
    }

    @PostMapping
    public DataResult<AddCategoryResponse> addCategory(@RequestBody @Valid AddCategoryRequest addCategoryRequest) throws Exception {
        return categoryService.addCategory(addCategoryRequest);
    }

    @PutMapping("")
    public DataResult<UpdateCategoryResponse> updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) throws Exception {
        return categoryService.updateCategory(updateCategoryRequest);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Long id) {
        return categoryService.delete(id);
    }
}
