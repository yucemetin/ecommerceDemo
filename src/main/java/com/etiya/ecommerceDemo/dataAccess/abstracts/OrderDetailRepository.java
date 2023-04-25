package com.etiya.ecommerceDemo.dataAccess.abstracts;

import com.etiya.ecommerceDemo.entities.concrete.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query(value = "SELECT od.* FROM order_details od INNER JOIN order o ON o.order_id = od.order_id", nativeQuery = true)
    List<OrderDetail> getAllOrderDetails();
}
