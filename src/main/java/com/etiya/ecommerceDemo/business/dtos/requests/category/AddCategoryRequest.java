package com.etiya.ecommerceDemo.business.dtos.requests.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {

    @NotEmpty(message = "{categoryNameRequire}")
    private String name;
}
