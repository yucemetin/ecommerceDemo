package com.etiya.ecommerceDemo.business.dtos.requests.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {

    @NotEmpty(message = "Order date can not be empty")
    private Date orderDate;

    @NotBlank(message = "User can not be empty")
    private Long userId;
}
