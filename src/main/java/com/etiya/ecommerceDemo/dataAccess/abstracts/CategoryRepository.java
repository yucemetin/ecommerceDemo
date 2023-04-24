package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
