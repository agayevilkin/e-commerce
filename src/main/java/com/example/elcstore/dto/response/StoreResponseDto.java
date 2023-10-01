package com.example.elcstore.dto.response;

import com.example.elcstore.domain.enums.StoreLocationStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class StoreResponseDto {

    private UUID id;
    private String title;
    private String address;
    private String workingHours;
    private StoreLocationStatus storeLocationStatus;
}
