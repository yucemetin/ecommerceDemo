package com.etiya.ecommerceDemo.business.abstracts;

import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;

import java.util.List;

public interface OrderService {
    List<ListOrderResponse> getAll();

    OrderDetailResponse getById(Long id);

    AddOrderResponse addOrder(AddOrderRequest addOrderRequest);
}
