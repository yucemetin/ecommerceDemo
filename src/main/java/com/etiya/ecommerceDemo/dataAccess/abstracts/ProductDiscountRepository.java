package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Product;
import com.etiya.ecommerceDemo.entities.concrete.ProductDiscount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDiscountRepository extends JpaRepository<ProductDiscount,Integer> {
    @Query(value = "SELECT * FROM public.product_discount")
    List<Product> getProductDiscount();
    @Query(value = "SELECT * FROM public.product_discount pd where pd.discount_amount = (select MAX(pd2.discount_amount) FROM public.product_discount pd2)")
    List<ProductDiscount> getMaxProductDiscount();

}
