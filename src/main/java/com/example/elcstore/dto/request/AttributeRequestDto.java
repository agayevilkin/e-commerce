
package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeRequestDto {

    @NotBlank(message = "Please provide a value")
    private String value;

    @NotNull
    private UUID attributeDefinitionId;

}
