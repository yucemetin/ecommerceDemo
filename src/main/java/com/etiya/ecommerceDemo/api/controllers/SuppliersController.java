package com.etiya.ecommerceDemo.api.controllers;

import com.etiya.ecommerceDemo.business.abstracts.SupplierService;
import com.etiya.ecommerceDemo.business.dtos.requests.supplier.AddSupplierRequest;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.AddSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.ListSupplierResponse;
import com.etiya.ecommerceDemo.business.dtos.responses.supplier.SupplierDetailResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/suppliers")
@AllArgsConstructor
public class SuppliersController {
    private SupplierService supplierService;

    @GetMapping
    public List<ListSupplierResponse> getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{id}")
    public SupplierDetailResponse getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @PostMapping
    public AddSupplierResponse addProduct(@RequestBody @Valid AddSupplierRequest addSupplierRequest) throws Exception {
        return supplierService.addSupplier(addSupplierRequest);
    }
}
