package com.etiya.ecommerceDemo.repository.contretes;

import com.etiya.ecommerceDemo.entities.concrete.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewDao extends JpaRepository<ProductReview, Long> {
}
