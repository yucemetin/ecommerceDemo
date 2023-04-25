package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.entities.concretes.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();

    Order getById(Long id);

    void addOrder(Order order);
}
