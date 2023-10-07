package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardOptionInfoUpdateDto {

    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
}
