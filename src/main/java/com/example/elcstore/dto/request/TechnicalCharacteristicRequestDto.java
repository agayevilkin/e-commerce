package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TechnicalCharacteristicRequestDto {

    @NotBlank(message = "characteristicName is Required!")
    private String characteristicName;

    @NotBlank(message = "value is Required!")
    private String value;

    @NotNull
    private UUID technicalCharacteristicTitleId;
}
