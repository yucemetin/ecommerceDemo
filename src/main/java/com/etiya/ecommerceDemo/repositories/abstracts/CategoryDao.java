package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.* FROM categories c INNER JOIN products p on p.id = c.id", nativeQuery = true)
    List<Category> getCategoriesByProductId();

    Category findByName(String name);
}
