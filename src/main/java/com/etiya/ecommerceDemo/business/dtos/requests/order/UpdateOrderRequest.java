package com.etiya.ecommerceDemo.business.dtos.requests.order;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {

    private Long id;

    @NotEmpty(message = "{userRequire}")
    private Long userId;
}
