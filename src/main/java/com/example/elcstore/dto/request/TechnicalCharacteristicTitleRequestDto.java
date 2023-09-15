package com.example.elcstore.dto.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TechnicalCharacteristicTitleRequestDto {

    @NotBlank(message = "name is Required")
    public String name;
}
