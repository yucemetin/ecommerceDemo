package com.etiya.ecommerceDemo.business.dtos.requests.supplier;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSupplierRequest {

    @NotEmpty(message = "{supplierNameRequire}")
    private String supplierName;
}
