package com.example.elcstore.dto.request;

import jakarta.annotation.Nullable;
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

    @NotBlank(message = "firstName Description is required")
    private String firstName;

    @NotBlank(message = "lastName Description is required")
    private String lastName;

    @NotBlank(message = "username Description is required")
    @Size(min = 5)
    private String username;

    @NotBlank(message = "User Description is required")
    @Size(min = 5)
    private String password;

    @NotBlank(message = "User Description is required")
    private String userDescription;

    @NotBlank(message = "Email is required")
    private String email;

    private List<UUID> roles;

    @Nullable
    private MultipartFile image;

}
