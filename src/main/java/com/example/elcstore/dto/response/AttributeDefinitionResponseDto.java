package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class AttributeDefinitionResponseDto {
    private UUID id;
    private String name;
    private String type;
}
