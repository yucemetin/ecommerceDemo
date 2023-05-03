package com.etiya.ecommerceDemo.business.dtos.requests.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddProductRequest {

    @NotEmpty(message = "{productNameRequire}")
    private String name;

    @NotEmpty(message = "{unitPriceRequire}")
    private double unitPrice;

    @NotEmpty(message = "{categoryIdRequire}")
    private Long categoryId;
}
