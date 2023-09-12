package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRequestDto {

    @NotBlank(message = "Please provide a name")
    private String name;

}
