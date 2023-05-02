package com.etiya.ecommerceDemo.business.dtos.requests.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {

    private Long id;
    @NotEmpty(message = "Product name can not be empty!")
    private String name;

    private double unitPrice;

    private Long categoryId;
}
