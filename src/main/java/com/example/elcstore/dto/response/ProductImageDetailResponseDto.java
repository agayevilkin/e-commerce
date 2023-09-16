package com.example.elcstore.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ProductImageDetailResponseDto {
    private UUID id;
    private UUID imageId;
    //todo can be add ProductOption
    private int orderNum;
}
