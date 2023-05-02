package com.etiya.ecommerceDemo.business.dtos.requests.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {

    private Long id;

    @NotBlank(message = "User can not be empty")
    private Long userId;
}
