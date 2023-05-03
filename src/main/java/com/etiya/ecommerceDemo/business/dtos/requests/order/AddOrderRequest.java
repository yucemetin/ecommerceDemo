package com.etiya.ecommerceDemo.business.dtos.requests.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddOrderRequest {

    @NotEmpty(message = "{orderDateRequire}")
    private Date orderDate;

    @NotEmpty(message = "{userRequire}")
    private Long userId;
}
