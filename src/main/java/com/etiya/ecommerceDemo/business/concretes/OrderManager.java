package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.OrderService;
import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.ErrorDataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.Order;
import com.etiya.ecommerceDemo.repositories.abstracts.OrderDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private OrderDao orderDao;
    private ModelMapperService modelMapperService;

    @Override
    public DataResult<List<ListOrderResponse>> getAll() {
        return new SuccessDataResult<>(orderDao.getAll(), "Siparişler başarılı bir şekilde listelendi.");
    }

    @Override
    public DataResult<OrderDetailResponse> getById(Long id) {
        if (orderDao.getOneOrder(id) == null) {
            return new ErrorDataResult<>(orderDao.getOneOrder(id), "Sipariş bulunamadı.");
        }
        return new SuccessDataResult<>(orderDao.getOneOrder(id), "Sipariş başarılı bir şekilde listelendi.");
    }

    @Override
    public DataResult<AddOrderResponse> addOrder(AddOrderRequest addOrderRequest) {

        Order order = this.modelMapperService.getMapper().map(addOrderRequest, Order.class);

        orderDao.save(order);

        AddOrderResponse addOrderResponse = this.modelMapperService.getMapper().map(order, AddOrderResponse.class);

        return new SuccessDataResult<>(addOrderResponse, "Sipariş başarılı bir şekild eklendi.");
    }
}
