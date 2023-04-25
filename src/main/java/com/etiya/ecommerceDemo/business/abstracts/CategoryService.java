package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    void addCategory(Category category);
}
