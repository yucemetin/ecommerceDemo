package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.CategoryService;
import com.etiya.ecommerceDemo.entities.concretes.Category;
import com.etiya.ecommerceDemo.repositories.abstracts.CategoryDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category getById(Long id) {
        return categoryDao.findById(id).orElseThrow();
    }

    @Override
    public void addCategory(Category category) throws Exception {

        if (categoryDao.findByName(category.getName()) != null) {
            throw new Exception("Girdiğiniz kategori zaten mevcut");
        }

        categoryDao.save(category);
    }
}
