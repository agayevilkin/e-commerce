
package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class HighlightRequestDto {

    @NotBlank(message = "Please provide a value")
    private String value;

    @NotBlank(message = "Please provide a productIdentificationName")
    private String productIdentificationName;

    @NotNull
    private UUID highlightDefinitionId;

}
