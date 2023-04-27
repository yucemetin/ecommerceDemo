package com.etiya.ecommerceDemo.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailResponse {
    private Long id;
    private String first_name;

    private String last_name;

    private String email;

    private String password;
}
