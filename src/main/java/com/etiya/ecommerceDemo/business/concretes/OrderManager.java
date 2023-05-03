package com.etiya.ecommerceDemo.business.concretes;

import com.etiya.ecommerceDemo.business.abstracts.OrderService;
import com.etiya.ecommerceDemo.business.abstracts.UserService;
import com.etiya.ecommerceDemo.business.constants.Messages;
import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.order.UpdateOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.UpdateOrderResponse;
import com.etiya.ecommerceDemo.core.exceptions.types.NotFoundException;
import com.etiya.ecommerceDemo.core.internationalization.MessageService;
import com.etiya.ecommerceDemo.core.utils.mapper.ModelMapperService;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.SuccessDataResult;
import com.etiya.ecommerceDemo.entities.concretes.Order;
import com.etiya.ecommerceDemo.repositories.abstracts.OrderDao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderManager implements OrderService {
    private OrderDao orderDao;
    private ModelMapperService modelMapperService;
    private MessageService messageService;
    private UserService userService;

    @Override
    public DataResult<List<ListOrderResponse>> getAll() {
        return new SuccessDataResult<>(orderDao.getAll(), messageService.getMessage(Messages.Order.successListOrder));
    }

    @Override
    public DataResult<Slice<ListOrderResponse>> getAllWithPage(Pageable pageable) {
        return new SuccessDataResult<>(orderDao.getAllWithPagination(pageable), messageService.getMessage(Messages.Order.successListOrder));
    }

    @Override
    public DataResult<OrderDetailResponse> getById(Long id) throws Exception {
        checkIfOrderIdExists(id);

        return new SuccessDataResult<>(orderDao.getOneOrder(id), messageService.getMessage(Messages.Order.successOneOrder));
    }

    @Override
    public DataResult<AddOrderResponse> addOrder(AddOrderRequest addOrderRequest) {

        userService.checkIfUserIdExists(addOrderRequest.getUserId());

        Order order = this.modelMapperService.getMapper().map(addOrderRequest, Order.class);

        orderDao.save(order);

        AddOrderResponse addOrderResponse = this.modelMapperService.getMapper().map(order, AddOrderResponse.class);

        return new SuccessDataResult<>(addOrderResponse, messageService.getMessage(Messages.Order.successAddOrder));
    }

    @Override
    public DataResult<UpdateOrderResponse> updateOrder(UpdateOrderRequest updateOrderRequest) {

        checkIfOrderIdExists(updateOrderRequest.getId());

        Order order = modelMapperService.getMapper().map(updateOrderRequest, Order.class);

        orderDao.save(order);

        UpdateOrderResponse updateOrderResponse = this.modelMapperService.getMapper().map(order, UpdateOrderResponse.class);

        return new SuccessDataResult<>(updateOrderResponse, messageService.getMessage(Messages.Order.successUpdateOrder));
    }

    public void checkIfOrderIdExists(Long id) {
        if (!orderDao.existsById(id)) {
            throw new NotFoundException(messageService.getMessage(Messages.Order.errorOneOrder));
        }
    }
}
