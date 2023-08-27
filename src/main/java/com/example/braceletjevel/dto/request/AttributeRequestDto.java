package com.example.braceletjevel.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttributeRequestDto {

    @NotBlank(message = "Please provide a name")
    private String name;

    @NotBlank(message = "Please provide a name")
    private String value;
}
