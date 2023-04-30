package com.etiya.ecommerceDemo.business.dtos.responses.supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSupplierResponse {

    private Long id;
    private String supplierName;
    
}
