package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AttributeResponseDto {
    private UUID id;
    private String value;
    private AttributeDefinitionResponseDto attributeDefinition;
}
