package com.example.elcstore.dto.response;

import com.example.elcstore.domain.TechnicalCharacteristicTitle;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class TechnicalCharacteristicResponseDto {

    private UUID id;
    private String characteristicName;
    private String value;
    private TechnicalCharacteristicTitle title;
}
