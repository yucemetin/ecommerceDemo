package com.etiya.ecommerceDemo.repositories.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewDao extends JpaRepository<ProductReview,Integer> {
}
