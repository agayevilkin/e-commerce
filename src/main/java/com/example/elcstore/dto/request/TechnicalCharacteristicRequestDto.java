package com.example.elcstore.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TechnicalCharacteristicRequestDto {

    @NotBlank(message = "content is Required!")
    private String content;

    @NotNull
    private UUID technicalCharacteristicTitleId;
}
