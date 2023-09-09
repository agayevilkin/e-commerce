package com.example.elcstore.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class UserEmployeeRequestDto {

    @NotBlank(message = "firstName is required")
    private String firstName;

    @NotBlank(message = "lastName is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 5)
    private String password;

    @NotBlank(message = "User is required")
    private String userDescription;

    //todo create 2. request dto for create employee service without this field
    private List<UUID> roles;

    @Nullable
    private MultipartFile image;

}
