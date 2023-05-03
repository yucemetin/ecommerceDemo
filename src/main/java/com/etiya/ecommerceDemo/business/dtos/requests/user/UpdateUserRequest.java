package com.etiya.ecommerceDemo.business.dtos.requests.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    private Long id;

    @NotBlank(message = "{firstNameRequire}")
    private String first_name;

    @NotBlank(message = "{lastNameRequire}")
    private String last_name;

    @Email
    @NotEmpty(message = "{emailRequire}")
    private String email;

    @NotEmpty(message = "{passwordRequire}")
    private String password;
}
