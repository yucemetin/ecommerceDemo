package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.OrderService;
import com.etiya.ecommerceDemo.business.dtos.requests.order.AddOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.requests.order.UpdateOrderRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.order.AddOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.ListOrderResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.OrderDetailResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.order.UpdateOrderResponse;
import com.etiya.ecommerceDemo.core.utils.result.DataResult;
import com.etiya.ecommerceDemo.core.utils.result.Result;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@AllArgsConstructor
public class OrdersController {

    private OrderService orderService;

    @GetMapping
    public DataResult<List<ListOrderResponse>> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/page")
    public DataResult<Slice<ListOrderResponse>> getAllWithPage(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return orderService.getAllWithPage(pageable);
    }

    @GetMapping("/{id}")
    public DataResult<OrderDetailResponse> getById(@PathVariable Long id) throws Exception {
        return orderService.getById(id);
    }

    @PostMapping
    public DataResult<AddOrderResponse> addCategory(@RequestBody AddOrderRequest addOrderRequest) {
        return orderService.addOrder(addOrderRequest);
    }

    @PutMapping("")
    public DataResult<UpdateOrderResponse> updateOrder(@RequestBody @Valid UpdateOrderRequest updateOrderRequest) throws Exception {
        return orderService.updateOrder(updateOrderRequest);
    }

    @DeleteMapping("/{id}")
    public Result deleteOrder(@PathVariable Long id) {
        return orderService.deleteOrder(id);
    }
}
