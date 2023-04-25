package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query(value = "SELECT o.* FROM orders o INNER JOIN returns r ON o.id = r.order_id", nativeQuery = true)
    List<Order> getOrderByReturns();

}
