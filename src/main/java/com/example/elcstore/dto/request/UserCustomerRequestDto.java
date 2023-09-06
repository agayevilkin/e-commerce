package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserCustomerRequestDto {
    @NotBlank(message = "username Description is required")
    @Size(min = 5)
    private String username;

    @NotBlank(message = "User Description is required")
    @Size(min = 5)
    private String password;

}
