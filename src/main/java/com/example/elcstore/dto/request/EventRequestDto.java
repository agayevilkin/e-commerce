package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EventRequestDto {

    @NotBlank(message = "Please provide a name")
    private String name;

    @NotNull
    private UUID colorId;
}
