package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Order;
import com.etiya.ecommerceDemo.entities.concrete.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReturnRepository extends JpaRepository<Return,Integer> {

    @Query(value = "SELECT r.* FROM orders o INNER JOIN returns r ON o.id = r.order_id", nativeQuery = true)
    List<Return> getOrderByReturns();
}
