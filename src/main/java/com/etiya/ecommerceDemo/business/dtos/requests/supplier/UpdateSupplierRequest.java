package com.etiya.ecommerceDemo.business.dtos.requests.supplier;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplierRequest {

    private Long id;
    @NotEmpty(message = "{supplierNameRequire}")
    private String supplierName;
}
