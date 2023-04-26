package com.etiya.ecommerceDemo.business.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddUserRequest {
    private String first_name;

    private String last_name;

    private String email;

    private String password;
}
