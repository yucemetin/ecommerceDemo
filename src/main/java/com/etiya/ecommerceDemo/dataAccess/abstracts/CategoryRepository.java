package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(value = "SELECT c.* FROM categories c INNER JOIN products p on p.id = c.id", nativeQuery = true)
    List<Category> getCategoriesByProductId();
}
