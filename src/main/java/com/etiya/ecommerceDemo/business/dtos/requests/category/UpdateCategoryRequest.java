package com.etiya.ecommerceDemo.business.dtos.requests.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCategoryRequest {

    private Long id;
    @NotEmpty(message = "{categoryNameRequire}")
    private String name;
}
