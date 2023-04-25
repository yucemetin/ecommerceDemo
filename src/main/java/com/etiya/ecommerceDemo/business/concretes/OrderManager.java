package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.OrderService;
import com.etiya.ecommerceDemo.entities.concretes.Order;
import com.etiya.ecommerceDemo.repositories.abstracts.OrderDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private OrderDao orderDao;

    @Override
    public List<Order> getAll() {
        return orderDao.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderDao.findById(id).orElseThrow();
    }

    @Override
    public void addOrder(Order order) {
        orderDao.save(order);
    }
}
